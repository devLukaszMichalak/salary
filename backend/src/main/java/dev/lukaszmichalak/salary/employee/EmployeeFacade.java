package dev.lukaszmichalak.salary.employee;

import dev.lukaszmichalak.salary.employee.dto.EmployeeDto;
import java.util.List;

public interface EmployeeFacade {
  List<EmployeeDto> getEmployees();

  EmployeeDto getEmployee(Long employeeId);
  
  List<String> getAgencyNames();
  
  List<EmployeeDto> getEmployeesOfAgency(String agencyName);
}
