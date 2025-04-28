package dev.lukaszmichalak.salary.error;

import dev.lukaszmichalak.salary.employee.exception.EmployeeNotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class GlobalExceptionHandler {

  @ExceptionHandler(EmployeeNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  ErrorResponse handle(EmployeeNotFoundException e) {
    return new ErrorResponse(
        HttpStatus.NOT_FOUND.value(),
        "Employee Not Found",
        "Check that the requested ID exists and try again.");
  }

  @ExceptionHandler(UsernameNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  ErrorResponse handle(UsernameNotFoundException e) {
    return new ErrorResponse(
        HttpStatus.NOT_FOUND.value(),
        "Username Not Found",
        "Verify the email address and try again.");
  }

  @ExceptionHandler(ExpiredJwtException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  ErrorResponse handle(ExpiredJwtException e) {
    return new ErrorResponse(
        HttpStatus.UNAUTHORIZED.value(),
        "Session Expired",
        "Your session has expired. Please log in again to continue.");
  }

  @ExceptionHandler(exception = {JwtException.class, MalformedJwtException.class})
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  ErrorResponse handle(JwtException e) {
    return new ErrorResponse(
        HttpStatus.UNAUTHORIZED.value(),
        "Authentication Token Error",
        "Invalid authentication token. Please log in again to continue.");
  }

  @ExceptionHandler(BadCredentialsException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  ErrorResponse handle(BadCredentialsException e) {
    return new ErrorResponse(
        HttpStatus.UNAUTHORIZED.value(),
        "Authentication Error",
        "Invalid credentials. Please check your email and password and try again.");
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  ErrorResponse handle(Exception e) {
    return new ErrorResponse(
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        "Server Error",
        "An unexpected error occurred. Please try again later. If the problem persists, contact our support team.");
  }
}
