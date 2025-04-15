package dev.lukaszmichalak.salary.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

interface UserRepository extends ListCrudRepository<User, Long> {

  Optional<User> findUserByEmail(String email);

  boolean existsByEmail(String email);

  void deleteUserByEmail(String email);

  @Transactional
  @Modifying
  @Query("UPDATE User u SET u.email = :email, u.password = :password WHERE u.id = :id")
  void updateEmailAndPasswordById(
      @Param("email") String email, @Param("password") String password, @Param("id") Long id);
}
