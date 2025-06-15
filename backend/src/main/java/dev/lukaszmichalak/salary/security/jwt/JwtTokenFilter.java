package dev.lukaszmichalak.salary.security.jwt;

import io.vavr.control.Try;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;
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
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain)
      throws ServletException, IOException {

    final Optional<String> tokenOpt = getAuthToken(request);

    tokenOpt.ifPresent(
        token ->
            Try.run(
                    () -> {
                      String email = jwtClaimsExtractor.extractEmail(token);

                      Authentication authentication =
                          SecurityContextHolder.getContext().getAuthentication();

                      if (email != null && authentication == null) {

                        if (jwtService.isTokenValid(token, email)) {
                          UsernamePasswordAuthenticationToken authToken =
                              UsernamePasswordAuthenticationToken.authenticated(
                                  email, null, List.of());

                          authToken.setDetails(
                              new WebAuthenticationDetailsSource().buildDetails(request));
                          SecurityContextHolder.getContext().setAuthentication(authToken);
                        }
                      }
                    })
                .onFailure(_ -> SecurityContextHolder.clearContext()));

    filterChain.doFilter(request, response);
  }

  private Optional<String> getAuthToken(HttpServletRequest request) {
    return Optional.of(request)
        .map(r -> r.getHeader("Authorization"))
        .filter(h -> h.startsWith("Bearer "))
        .map(h -> h.substring(7));
  }
}
