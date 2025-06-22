package dev.lukaszmichalak.salary.gateway.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record LoginUserCommand(
    @NotNull(message = "Email is required")
    @Email(message = "Invalid email format")
    String email,
    @NotNull(message = "Password is required")
    String password
) {}
