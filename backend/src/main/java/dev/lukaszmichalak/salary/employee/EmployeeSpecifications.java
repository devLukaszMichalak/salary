package dev.lukaszmichalak.salary.employee;

import dev.lukaszmichalak.salary.employee.dto.EmployeeSearchCriteria;
import org.springframework.data.jpa.domain.Specification;

final class EmployeeSpecifications {

  private EmployeeSpecifications() {
    throw new UnsupportedOperationException();
  }

  static Specification<Employee> fromSearchCriteria(EmployeeSearchCriteria criteria) {
    Specification<Employee> spec = (_, _, cb) -> cb.conjunction();

    if (criteria.name() != null && !criteria.name().isBlank()) {
      spec = spec.and(hasNameContaining(criteria.name()));
    }

    if (criteria.positionTitle() != null && !criteria.positionTitle().isBlank()) {
      spec = spec.and(hasPositionTitleContaining(criteria.positionTitle()));
    }

    if (criteria.agencyName() != null && !criteria.agencyName().isBlank()) {
      spec = spec.and(hasAgencyNameContaining(criteria.agencyName()));
    }

    return spec;
  }

  static Specification<Employee> hasAgencyNameContaining(String agencyName) {
    return (root, _, cb) ->
        cb.like(cb.lower(root.get("agency").get("name")), "%" + agencyName.toLowerCase() + "%");
  }

  static Specification<Employee> hasPositionTitleContaining(String position) {
    return (root, _, cb) ->
        cb.like(cb.lower(root.get("position").get("title")), "%" + position.toLowerCase() + "%");
  }

  static Specification<Employee> hasNameContaining(String name) {
    return (root, _, cb) -> cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
  }
}
