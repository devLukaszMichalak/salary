package dev.lukaszmichalak.salary.employee;

import dev.lukaszmichalak.salary.employee.dto.EmployeeDto;

class EmployeeMapper {

  static EmployeeDto map(Employee employee) {
    return new EmployeeDto(
        employee.getId(),
        employee.getName(),
        employee.getPosition().getTitle(),
        employee.getAgency().getName());
  }
}
