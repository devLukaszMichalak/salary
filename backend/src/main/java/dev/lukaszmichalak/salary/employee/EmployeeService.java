package dev.lukaszmichalak.salary.employee;

import dev.lukaszmichalak.salary.employee.dto.EmployeeDto;
import java.util.List;

public interface EmployeeService {
  List<EmployeeDto> getEmployees();

  EmployeeDto getEmployee(Long id);
}
