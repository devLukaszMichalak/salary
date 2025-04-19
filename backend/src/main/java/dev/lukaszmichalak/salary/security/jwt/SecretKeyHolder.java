package dev.lukaszmichalak.salary.security.jwt;

import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class SecretKeyHolder {

  @Value("${jwt.secret-key}")
  private String secretKey;

  SecretKey get() {
    return Keys.hmacShaKeyFor(secretKey.getBytes());
  }
}
