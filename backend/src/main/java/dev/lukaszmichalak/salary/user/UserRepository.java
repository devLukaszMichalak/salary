package dev.lukaszmichalak.salary.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

interface UserRepository extends ListCrudRepository<User, Long> {

  Optional<User> findUserByEmail(String email);

  @Query(value = "SELECT EXISTS(SELECT email FROM users WHERE email = :email)", nativeQuery = true)
  boolean existsByEmail(@Param("email") String email);

  void deleteUserByEmail(String email);

  @Transactional
  @Modifying
  @Query("UPDATE User u SET u.email = :email, u.password = :password WHERE u.id = :id")
  void updateEmailAndPasswordById(
      @Param("email") String email, @Param("password") String password, @Param("id") Long id);
}
