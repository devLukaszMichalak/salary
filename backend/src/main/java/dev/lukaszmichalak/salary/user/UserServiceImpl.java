package dev.lukaszmichalak.salary.user;

import dev.lukaszmichalak.salary.gateway.command.LoginUserCommand;
import dev.lukaszmichalak.salary.gateway.command.RegisterUserCommand;
import dev.lukaszmichalak.salary.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserDetailsManager userDetailsManager;
  private final AuthenticationManager authenticationManager;

  @Override
  public UserDto getUser(String email) {
    return userRepository
        .findUserByEmail(email)
        .map(user -> new UserDto(user.getId(), user.getEmail()))
        .orElseThrow(() -> new UsernameNotFoundException(email));
  }

  @Override
  @Cacheable("emailExists")
  public boolean doesUserExists(String email) {
    return userDetailsManager.userExists(email);
  }

  @Override
  @CacheEvict(value = "emailExists", key = "#cmd.email()")
  public UserDto register(RegisterUserCommand cmd) {

    if (!cmd.password().equals(cmd.repeatPassword())) {
      throw new IllegalArgumentException("Passwords do not match!");
    }

    var encodedPassword = passwordEncoder.encode(cmd.password());
    User user = new User(cmd.email(), encodedPassword);
    userDetailsManager.createUser(user);

    return login(new LoginUserCommand(cmd.email(), cmd.password()));
  }

  @Override
  public UserDto login(LoginUserCommand cmd) {

    var unauthenticated =
        UsernamePasswordAuthenticationToken.unauthenticated(cmd.email(), cmd.password());
    var authenticated = authenticationManager.authenticate(unauthenticated);

    var securityContext = SecurityContextHolder.getContext();
    securityContext.setAuthentication(authenticated);

    return getUser(cmd.email());
  }

  @Override
  public void logout() {
    SecurityContextHolder.clearContext();
  }
}
