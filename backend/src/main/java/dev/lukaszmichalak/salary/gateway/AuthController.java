package dev.lukaszmichalak.salary.gateway;

import dev.lukaszmichalak.salary.user.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
class AuthController {

  private final RegistrationService registrationService;

  @GetMapping("/check-email")
  public boolean checkEmail(@RequestParam String email) {
    return registrationService.doesUserExists(email);
  }
}
