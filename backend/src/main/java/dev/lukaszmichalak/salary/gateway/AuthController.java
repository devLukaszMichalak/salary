package dev.lukaszmichalak.salary.gateway;

import dev.lukaszmichalak.salary.gateway.request.LoginUserCommand;
import dev.lukaszmichalak.salary.gateway.request.RegisterUserCommand;
import dev.lukaszmichalak.salary.gateway.response.JwtResponse;
import dev.lukaszmichalak.salary.security.jwt.JwtService;
import dev.lukaszmichalak.salary.user.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
class AuthController {

  private final UserService userService;
  private final JwtService jwtService;

  @GetMapping("/is-email-taken")
  public boolean isEmailTaken(
      @RequestParam
      @NotNull(message = "Email is required")
      @Email(message = "Invalid email format")
      String email) {
    return userService.doesUserExists(email);
  }

  @PostMapping("/register")
  public JwtResponse register(@Valid @RequestBody RegisterUserCommand registerUserCommand) {
    var user = userService.register(registerUserCommand);
    var token = jwtService.generateToken(user);
    return new JwtResponse(token);
  }

  @PostMapping("/login")
  public JwtResponse login(@Valid @RequestBody LoginUserCommand loginUserCommand) {
    var user = userService.login(loginUserCommand);
    var token = jwtService.generateToken(user);
    return new JwtResponse(token);
  }

  @PostMapping("/validate-token")
  public boolean validateToken(
      @RequestHeader("Authorization")
      @NotNull(message = "Authorization header is required")
      @Pattern(regexp = "^Bearer .+$", message = "Authorization header must start with 'Bearer '")
      String authHeader) {
    String token = authHeader.substring(7);
    return jwtService.isTokenValid(token);
  }
}
