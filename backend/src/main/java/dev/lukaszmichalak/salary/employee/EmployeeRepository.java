package dev.lukaszmichalak.salary.employee;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.ListCrudRepository;

interface EmployeeRepository
    extends ListCrudRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

  @Nonnull
  @Override
  @EntityGraph(value = "Employee.full")
  Optional<Employee> findById(@Nonnull Long id);

  @Nonnull
  @Override
  @EntityGraph(value = "Employee.full")
  List<Employee> findAll();

  @Nonnull
  @Override
  @EntityGraph(value = "Employee.full")
  Page<Employee> findAll(@Nullable Specification<Employee> spec, @Nonnull Pageable pageable);

  @Nonnull
  @Override
  @EntityGraph(value = "Employee.full")
  List<Employee> findAll(@Nullable Specification<Employee> spec);

  @Nonnull
  @Override
  @EntityGraph(value = "Employee.full")
  List<Employee> findAllById(@Nonnull Iterable<Long> ids);

  @EntityGraph(value = "Employee.full")
  List<Employee> findAllByAgencyName(@Nonnull String agencyName);
}
