package dev.lukaszmichalak.salary.gateway;

import dev.lukaszmichalak.salary.gateway.command.LoginUserCommand;
import dev.lukaszmichalak.salary.gateway.command.RegisterUserCommand;
import dev.lukaszmichalak.salary.gateway.response.JwtResponse;
import dev.lukaszmichalak.salary.security.jwt.JwtService;
import dev.lukaszmichalak.salary.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
class AuthController {

  private final UserService userService;
  private final JwtService jwtService;

  @GetMapping("/is-email-taken")
  public boolean isEmailTaken(@RequestParam String email) {
    return userService.doesUserExists(email);
  }

  @PostMapping("/register")
  public JwtResponse register(@RequestBody RegisterUserCommand registerUserCommand) {
    var user = userService.register(registerUserCommand);
    var token = jwtService.generateToken(user);
    return new JwtResponse(token);
  }

  @PostMapping("/login")
  public JwtResponse login(@RequestBody LoginUserCommand loginUserCommand) {
    var user = userService.login(loginUserCommand);
    var token = jwtService.generateToken(user);
    return new JwtResponse(token);
  }

  @PostMapping("/validate-token")
  public boolean validateToken(@RequestHeader("Authorization") String authHeader) {
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      return false;
    }

    String token = authHeader.substring(7);
    return jwtService.isTokenValid(token);
  }
}
