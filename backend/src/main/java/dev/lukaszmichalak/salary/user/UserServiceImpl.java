package dev.lukaszmichalak.salary.user;

import dev.lukaszmichalak.salary.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public UserDto getUser(String email) {
    return userRepository
        .findUserByEmail(email)
        .map(user -> new UserDto(user.getId(), user.getEmail()))
        .orElseThrow(() -> new UsernameNotFoundException(email));
  }
}
