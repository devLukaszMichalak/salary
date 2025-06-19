package dev.lukaszmichalak.salary.employee;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

interface EmployeeRepository
    extends ListCrudRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

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
  Page<Employee> findAll(@Nullable Specification<Employee> spec, @NonNull Pageable pageable);

  @NonNull
  @Override
  @EntityGraph(value = "Employee.full")
  List<Employee> findAll(@Nullable Specification<Employee> spec);

  @NonNull
  @Override
  @EntityGraph(value = "Employee.full")
  List<Employee> findAllById(@NonNull Iterable<Long> ids);

  @EntityGraph(value = "Employee.full")
  List<Employee> findAllByAgencyName(@NonNull String agencyName);
}
