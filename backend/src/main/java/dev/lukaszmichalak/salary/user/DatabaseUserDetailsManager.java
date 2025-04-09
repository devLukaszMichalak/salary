package dev.lukaszmichalak.salary.user;

import dev.lukaszmichalak.salary.user.exceptions.PasswordChangeException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class DatabaseUserDetailsManager implements UserDetailsManager {

  private final UserRepository userRepository;

  @Override
  public void createUser(UserDetails user) {
    userRepository.save((User) user);
  }

  @Override
  public void updateUser(UserDetails user) {
    userRepository.updateEmailAndPasswordById(
        ((User) user).getEmail(), user.getPassword(), ((User) user).getId());
  }

  @Override
  public void deleteUser(String email) {
    userRepository.deleteUserByEmail(email);
  }

  @Override
  public void changePassword(String oldPassword, String newPassword)
      throws PasswordChangeException {
    throw new PasswordChangeException("Password change not supported!");
  }

  @Override
  public boolean userExists(String email) {
    return userRepository.existsByEmail(email);
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return userRepository
        .findUserByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException(email));
  }
}
