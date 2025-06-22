package dev.lukaszmichalak.salary.security.jwt;

import io.vavr.control.Option;
import io.vavr.control.Try;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

  private final JwtClaimsExtractor jwtClaimsExtractor;
  private final JwtService jwtService;

  @Override
  public void doFilterInternal(
      @Nonnull HttpServletRequest request,
      @Nonnull HttpServletResponse response,
      @Nonnull FilterChain filterChain)
      throws ServletException, IOException {

    Option<String> tokenOpt = getAuthToken(request);

    tokenOpt
        .toTry()
        .flatMap(token -> Try.run(() -> authenticate(request, token)))
        .onFailure(_ -> SecurityContextHolder.clearContext());

    filterChain.doFilter(request, response);
  }

  private Option<String> getAuthToken(HttpServletRequest request) {
    return Option.of(request)
        .map(r -> r.getHeader("Authorization"))
        .filter(h -> h.startsWith("Bearer "))
        .map(h -> h.substring(7));
  }

  private void authenticate(HttpServletRequest request, String token) {
    String email = jwtClaimsExtractor.extractEmail(token);

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (email != null && authentication == null) {

      if (jwtService.isTokenValid(token, email)) {
        var authToken = UsernamePasswordAuthenticationToken.authenticated(email, null, List.of());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }
  }
}
