package dev.lukaszmichalak.salary.gateway.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterUserCommand(
    @NotNull(message = "Email is required") @Email(message = "Invalid email format") String email,
    @NotNull(message = "Password is required")
        @Size(min = 8, message = "Minimal length is 8 characters")
        @Pattern(
            regexp = ".*[A-Z].*",
            message = "Password must contain at least one uppercase letter")
        @Pattern(
            regexp = ".*[a-z].*",
            message = "Password must contain at least one lowercase letter")
        @Pattern(regexp = ".*\\d.*", message = "Password must contain at least one number")
        String password,
    @NotNull(message = "Repetition of password is required") String repeatPassword) {}
