package dev.lukaszmichalak.salary.security.jwt;

import dev.lukaszmichalak.salary.user.dto.UserDto;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service()
@RequiredArgsConstructor
public class JwtService {

  private final Clock clock;
  private final SecretKeyHolder secretKeyHolder;
  private final JwtClaimsExtractor jwtClaimsExtractor;

  boolean isTokenValid(String token, String email) {
    final String username = jwtClaimsExtractor.extractEmail(token);
    return username.equals(email) && isTokenValid(token);
  }

  public String generateToken(UserDto user) {
    var now = Instant.now(clock);

    return Jwts.builder()
        .id(UUID.randomUUID().toString())
        .subject(user.email())
        .issuedAt(Date.from(now))
        .expiration(Date.from(now.plus(1, ChronoUnit.HOURS)))
        .signWith(secretKeyHolder.get())
        .compact();
  }

  public boolean isTokenValid(String token) {
    try{
      return jwtClaimsExtractor.extractExpiration(token).after(Date.from(Instant.now(clock)));
    } catch (ExpiredJwtException e) {
      return false;
    }
  }
}
