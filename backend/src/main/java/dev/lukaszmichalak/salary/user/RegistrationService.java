package dev.lukaszmichalak.salary.user;

public interface RegistrationService {

  boolean doesUserExists(String email);

  void register(String email, String password);
}
