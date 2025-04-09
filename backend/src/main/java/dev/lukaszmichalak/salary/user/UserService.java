package dev.lukaszmichalak.salary.user;

import dev.lukaszmichalak.salary.user.dto.UserDto;

public interface UserService {

  UserDto getUser(String email);
}
