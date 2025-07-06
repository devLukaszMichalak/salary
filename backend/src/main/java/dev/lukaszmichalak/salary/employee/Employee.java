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

@Getter()
@Entity
@Table(name = "employees")
@NoArgsConstructor
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

  @Column(nullable = false)
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @Cascade(CascadeType.PERSIST)
  @JoinColumn(nullable = false)
  private Position position;

  @ManyToOne(fetch = FetchType.LAZY)
  @Cascade(CascadeType.PERSIST)
  @JoinColumn(nullable = false)
  private Agency agency;

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
    Employee employee = (Employee) o;
    return getId() != null && Objects.equals(getId(), employee.getId());
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
    return "Employee(id = %s, name = %s, creationDate = %s, lastUpdatedOn = %s)"
        .formatted(id, name, creationDate, lastUpdateDate);
  }
}
