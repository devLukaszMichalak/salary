package dev.lukaszmichalak.salary.gateway;

import dev.lukaszmichalak.salary.salary.SalaryService;
import dev.lukaszmichalak.salary.salary.dto.SalaryDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/salaries")
@RequiredArgsConstructor
class SalaryController {

  private final SalaryService salaryService;

  @GetMapping
  List<SalaryDto> getSalaries() {
    return salaryService.getSalaries();
  }
}
