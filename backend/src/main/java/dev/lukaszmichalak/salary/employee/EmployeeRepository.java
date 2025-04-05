package dev.lukaszmichalak.salary.employee;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.lang.NonNull;

interface EmployeeRepository extends ListCrudRepository<Employee, Long> {

  @NonNull
  @Override
  @EntityGraph(value = "Employee.full")
  Optional<Employee> findById(@NonNull Long id);

  @NonNull
  @Override
  @EntityGraph(value = "Employee.full")
  List<Employee> findAll();

  @NonNull
  @Override
  @EntityGraph(value = "Employee.full")
  List<Employee> findAllById(@NonNull Iterable<Long> ids);
  
  @EntityGraph(value = "Employee.full")
  List<Employee> findAllByAgencyName(@NonNull String agencyName);
}
