package dev.lukaszmichalak.salary.security.jwt;

import dev.lukaszmichalak.salary.user.dto.UserDto;

public interface JwtService {
  boolean isTokenValid(String token, String email);

  String generateToken(UserDto user);

  boolean isTokenValid(String token);
}
