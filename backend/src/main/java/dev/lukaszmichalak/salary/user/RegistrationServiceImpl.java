package dev.lukaszmichalak.salary.user;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class RegistrationServiceImpl implements RegistrationService {

  private final UserDetailsManager userDetailsManager;
  private final AuthenticationManager authenticationManager;
  private final PasswordEncoder passwordEncoder;

  @Override
  public boolean doesUserExists(String email) {
    return userDetailsManager.userExists(email);
  }

  @Override
  public void register(String email, String password) {

    userDetailsManager.createUser(new User(email, passwordEncoder.encode(password)));

    var unauthenticated = UsernamePasswordAuthenticationToken.unauthenticated(email, password);
    var registered = authenticationManager.authenticate(unauthenticated);

    var securityContext = SecurityContextHolder.getContext();
    securityContext.setAuthentication(registered);
  }
}
