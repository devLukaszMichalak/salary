package dev.lukaszmichalak.salary.employee;

import dev.lukaszmichalak.salary.employee.dto.EmployeeDto;
import dev.lukaszmichalak.salary.employee.exception.EmployeeNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class EmployeeFacadeImpl implements EmployeeFacade {

  private final EmployeeRepository employeeRepository;
  private final AgencyRepository agencyRepository;
  private final EmployeeMapper employeeMapper;

  @Override
  public List<EmployeeDto> getEmployees() {
    return employeeRepository.findAll().stream().map(employeeMapper::toDto).toList();
  }

  @Override
  public EmployeeDto getEmployee(Long employeeId) {
    return employeeRepository
        .findById(employeeId)
        .map(employeeMapper::toDto)
        .orElseThrow(() -> new EmployeeNotFoundException(employeeId));
  }

  @Override
  public List<String> getAgencyNames() {
    return agencyRepository.findAll().stream().map(Agency::getName).toList();
  }

  @Override
  public List<EmployeeDto> getEmployeesOfAgency(String agencyName) {
    return employeeRepository.findAllByAgencyName(agencyName).stream()
        .map(employeeMapper::toDto)
        .toList();
  }
}
