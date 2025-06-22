package dev.lukaszmichalak.salary.gateway;

import dev.lukaszmichalak.salary.employee.EmployeeFacade;
import dev.lukaszmichalak.salary.employee.dto.EmployeeDto;
import dev.lukaszmichalak.salary.gateway.request.EmployeeQuery;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
class EmployeeController {

  private final EmployeeFacade employeeFacade;

  @GetMapping("/employees")
  List<EmployeeDto> getEmployees() {
    return employeeFacade.getEmployees();
  }

  @GetMapping("/employees/{employeeId}")
  EmployeeDto getEmployee(@PathVariable("employeeId") Long employeeId) {
    return employeeFacade.getEmployee(employeeId);
  }

  @GetMapping("/employees/search")
  Page<EmployeeDto> searchProducts(EmployeeQuery command, Pageable pageable) {
    return employeeFacade.getEmployeesBySpecification(command, pageable);
  }

  @GetMapping("/agencies")
  List<String> getAgencyNames() {
    return employeeFacade.getAgencyNames();
  }

  @GetMapping("/agencies/{agencyName}/employees")
  List<EmployeeDto> getEmployeesOfAgency(@PathVariable("agencyName") String agencyName) {
    return employeeFacade.getEmployeesOfAgency(agencyName);
  }
}
