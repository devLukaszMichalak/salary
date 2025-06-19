package dev.lukaszmichalak.salary.employee.dto;

import jakarta.annotation.Nullable;

public record EmployeeSearchCriteria(
    @Nullable String name, @Nullable String positionTitle, @Nullable String agencyName) {}
