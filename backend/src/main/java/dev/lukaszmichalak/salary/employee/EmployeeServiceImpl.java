package dev.lukaszmichalak.salary.employee;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;
  
  @PostConstruct
  public void init() {
    
    var agency = new Agency("COMMERCE AND INSURANCE");
    var position = new Position("SOFTWARE DEVELOPER");
    
    var employee = new Employee("MICHALAK, ŁUKASZ", position, agency);
    
    Employee saved = employeeRepository.save(employee);
    System.out.println(saved);
  }
}
