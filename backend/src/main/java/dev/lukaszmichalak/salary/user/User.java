package dev.lukaszmichalak.salary.user;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
class User implements UserDetails {

  User(String email, String password) {
    this.email = email;
    this.password = password;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Setter(AccessLevel.PACKAGE)
  @Column(name = "password", nullable = false)
  private String password;

  @CreationTimestamp(source = SourceType.DB)
  @Column(name = "creation_date", nullable = false, updatable = false)
  private LocalDateTime creationDate;

  @UpdateTimestamp(source = SourceType.DB)
  @Column(name = "last_update_date", nullable = false)
  private LocalDateTime lastUpdatedOn;

  @Override
  public String getUsername() {
    return getEmail();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of();
  }

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
    User user = (User) o;
    return getId() != null && Objects.equals(getId(), user.getId());
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
    return "User(id = %s, email = %s, password = %s, creationDate = %s, lastUpdatedOn = %s)"
        .formatted(id, email, password, creationDate, lastUpdatedOn);
  }
}
