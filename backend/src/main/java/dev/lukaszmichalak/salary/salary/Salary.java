package dev.lukaszmichalak.salary.salary;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.proxy.HibernateProxy;

@Getter()
@Entity
@Table(
    name = "salaries",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"year", "employee_id"})})
@NoArgsConstructor
class Salary {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private int year;

  @Column(nullable = false)
  private double yearlyGrossPay;

  @Column(nullable = false)
  private long employeeId;

  @CreationTimestamp(source = SourceType.DB)
  @Column(nullable = false, updatable = false)
  private LocalDateTime creationDate;

  @UpdateTimestamp(source = SourceType.DB)
  @Column(nullable = false)
  private LocalDateTime lastUpdateDate;

  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    Class<?> oEffectiveClass =
        o instanceof HibernateProxy hp
            ? hp.getHibernateLazyInitializer().getPersistentClass()
            : o.getClass();
    Class<?> thisEffectiveClass =
        this instanceof HibernateProxy hp
            ? hp.getHibernateLazyInitializer().getPersistentClass()
            : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) return false;
    Salary salary = (Salary) o;
    return getId() != null && Objects.equals(getId(), salary.getId());
  }

  @Override
  public final int hashCode() {
    var result =
        this instanceof HibernateProxy hp
            ? hp.getHibernateLazyInitializer().getPersistentClass().hashCode()
            : getClass().hashCode();

    return getId() == null ? result : 31 * result + Long.hashCode(getId());
  }

  @Override
  public String toString() {
    return "Salary(id = %s, year = %s, yearlyGrossPay = %s, employeeId = %s, creationDate = %s, lastUpdatedOn = %s)"
        .formatted(id, year, yearlyGrossPay, employeeId, creationDate, lastUpdateDate);
  }
}
