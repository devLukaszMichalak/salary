package dev.lukaszmichalak.salary.salary;

import dev.lukaszmichalak.salary.converter.LocalDateTimeConverter;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
    name = "salaries",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"year", "employee_id"})})
class Salary {

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "year", nullable = false)
  private int year;

  @Column(name = "yearly_gross_pay", nullable = false)
  private double yearlyGrossPay;

  @Column(name = "employee_id", nullable = false)
  private long employeeId;
  
  @Convert(converter = LocalDateTimeConverter.class)
  @Column(name = "creation_date", nullable = false, insertable = false, updatable = false)
  private LocalDateTime creationDate;
}
