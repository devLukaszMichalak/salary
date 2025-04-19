package dev.lukaszmichalak.salary.gateway.command;

public record RegisterUserCommand(String email, String password, String repeatPassword) {}
