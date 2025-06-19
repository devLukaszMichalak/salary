package dev.lukaszmichalak.salary.employee;

import dev.lukaszmichalak.salary.employee.dto.EmployeeDto;
import dev.lukaszmichalak.salary.employee.dto.EmployeeSearchCriteria;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeFacade {
  List<EmployeeDto> getEmployees();

  EmployeeDto getEmployee(Long employeeId);

  List<String> getAgencyNames();

  List<EmployeeDto> getEmployeesOfAgency(String agencyName);

  Page<EmployeeDto> getEmployeesBySpecification(EmployeeSearchCriteria criteria, Pageable pageable);
  
  List<EmployeeDto> getEmployeesBySpecification(EmployeeSearchCriteria criteria);
}
