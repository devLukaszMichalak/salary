package dev.lukaszmichalak.salary.salary;

import org.springframework.data.repository.ListCrudRepository;

interface SalaryRepository extends ListCrudRepository<Salary, Long> {}
