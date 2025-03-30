package dev.lukaszmichalak.salary.employee;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import org.hibernate.proxy.HibernateProxy;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "employees")
@NamedEntityGraph(
    name = "Employee.full",
    attributeNodes = {@NamedAttributeNode("position"), @NamedAttributeNode("agency")})
class Employee {

  Employee(String name, Position position, Agency agency) {
    this.name = name;
    this.position = position;
    this.agency = agency;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @Cascade(CascadeType.PERSIST)
  @JoinColumn(name = "position_id", nullable = false)
  private Position position;

  @ManyToOne(fetch = FetchType.LAZY)
  @Cascade(CascadeType.PERSIST)
  @JoinColumn(name = "agency_id", nullable = false)
  private Agency agency;

  @CreationTimestamp(source = SourceType.DB)
  @Column(name = "creation_date", nullable = false, updatable = false)
  private LocalDateTime creationDate;

  @UpdateTimestamp(source = SourceType.DB)
  @Column(name = "last_update_date", nullable = false)
  private LocalDateTime lastUpdatedOn;

  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    Class<?> oEffectiveClass =
        o instanceof HibernateProxy
            ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
            : o.getClass();
    Class<?> thisEffectiveClass =
        this instanceof HibernateProxy
            ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass()
            : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) return false;
    Employee employee = (Employee) o;
    return getId() != null && Objects.equals(getId(), employee.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy
        ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
        : getClass().hashCode();
  }

  @Override
  public String toString() {
    return getClass().getSimpleName()
        + "("
        + "id = "
        + id
        + ", "
        + "name = "
        + name
        + ", "
        + "creationDate = "
        + creationDate
        + ", "
        + "lastUpdatedOn = "
        + lastUpdatedOn
        + ")";
  }
}
