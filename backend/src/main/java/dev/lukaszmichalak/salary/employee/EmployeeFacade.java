package dev.lukaszmichalak.salary.employee;

import dev.lukaszmichalak.salary.employee.dto.EmployeeDto;
import dev.lukaszmichalak.salary.gateway.request.EmployeeQuery;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeFacade {
  List<EmployeeDto> getEmployees();

  EmployeeDto getEmployee(Long employeeId);

  List<String> getAgencyNames();

  List<EmployeeDto> getEmployeesOfAgency(String agencyName);

  Page<EmployeeDto> getEmployeesBySpecification(EmployeeQuery criteria, Pageable pageable);
  
  List<EmployeeDto> getEmployeesBySpecification(EmployeeQuery criteria);
}
