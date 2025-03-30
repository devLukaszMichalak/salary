package dev.lukaszmichalak.salary.gateway;

import dev.lukaszmichalak.salary.employee.EmployeeService;
import dev.lukaszmichalak.salary.employee.dto.EmployeeDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
class EmployeeController {

  private final EmployeeService employeeService;

  @GetMapping
  List<EmployeeDto> getEmployees() {
    return employeeService.getEmployees();
  }

  @GetMapping("/{id}")
  EmployeeDto getEmployee(@PathVariable("id") Long id) {
    return employeeService.getEmployee(id);
  }
}
