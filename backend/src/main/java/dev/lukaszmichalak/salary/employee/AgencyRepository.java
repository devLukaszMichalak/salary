package dev.lukaszmichalak.salary.employee;

import org.springframework.data.repository.ListCrudRepository;

interface AgencyRepository extends ListCrudRepository<Agency, Long> {}
