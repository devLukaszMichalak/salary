package dev.lukaszmichalak.salary.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
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

    final String authHeader = request.getHeader("Authorization");

    final String token =
        (authHeader != null && authHeader.startsWith("Bearer ")) ? authHeader.substring(7) : null;

    if (token != null) {

      try {

        String email = jwtClaimsExtractor.extractEmail(token);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (email != null && authentication == null) {

          if (jwtService.isTokenValid(token, email)) {
            UsernamePasswordAuthenticationToken authToken =
                UsernamePasswordAuthenticationToken.authenticated(email, null, List.of());

            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
          }
        }

      } catch (ExpiredJwtException e) {
        log.warn("JWT token is expired: {}", e.getMessage());
        SecurityContextHolder.clearContext();

      } catch (JwtException e) {
        log.warn("JWT token processing error: {}", e.getMessage());
        SecurityContextHolder.clearContext();

      } catch (Exception e) {
        log.error("Unexpected error during JWT filter processing", e);
        SecurityContextHolder.clearContext();
      }
    }

    filterChain.doFilter(request, response);
  }
}
