package dev.lukaszmichalak.salary.employee.exception;

public class EmployeeNotFoundException extends RuntimeException {

  public EmployeeNotFoundException(Long id) {
    super("Employee with id " + id + " not found");
  }
}
