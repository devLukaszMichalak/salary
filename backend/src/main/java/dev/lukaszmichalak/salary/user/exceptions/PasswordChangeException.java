package dev.lukaszmichalak.salary.user.exceptions;

public class PasswordChangeException extends RuntimeException {

  public PasswordChangeException(String message) {
    super(message);
  }
}
