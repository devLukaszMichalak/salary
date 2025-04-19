package dev.lukaszmichalak.salary.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class JwtClaimsExtractor {

  private final SecretKeyHolder secretKeyHolder;

  Claims extractAllClaims(String token) {
    return Jwts.parser()
        .verifyWith(secretKeyHolder.get())
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  String extractEmail(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }
}
