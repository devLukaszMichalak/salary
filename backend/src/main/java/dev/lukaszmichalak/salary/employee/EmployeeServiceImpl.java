package dev.lukaszmichalak.salary.employee;

import dev.lukaszmichalak.salary.employee.dto.EmployeeDto;
import dev.lukaszmichalak.salary.employee.exception.EmployeeNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;

  @Override
  public List<EmployeeDto> getEmployees() {
    return employeeRepository.findAll().stream().map(EmployeeMapper::map).toList();
  }

  @Override
  public EmployeeDto getEmployee(Long id) {
    return employeeRepository
        .findById(id)
        .map(EmployeeMapper::map)
        .orElseThrow(() -> new EmployeeNotFoundException(id));
  }
}
