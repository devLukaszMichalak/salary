package dev.lukaszmichalak.salary.error;

public record ErrorResponse(int status, String title, String message) {}
