package dev.lukaszmichalak.salary.user;

import dev.lukaszmichalak.salary.gateway.request.LoginUserCommand;
import dev.lukaszmichalak.salary.gateway.request.RegisterUserCommand;
import dev.lukaszmichalak.salary.user.dto.UserDto;

public interface UserService {

  boolean doesUserExists(String email);

  UserDto register(RegisterUserCommand cmd);

  UserDto login(LoginUserCommand cmd);

  UserDto getUser(String email);
}
