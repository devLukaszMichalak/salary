package dev.lukaszmichalak.salary.gateway.request;

import jakarta.annotation.Nullable;

public record EmployeeQuery(
    @Nullable String name, @Nullable String positionTitle, @Nullable String agencyName) {}
