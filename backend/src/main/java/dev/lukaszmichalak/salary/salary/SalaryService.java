package dev.lukaszmichalak.salary.salary;

import dev.lukaszmichalak.salary.salary.dto.SalaryDto;
import java.util.List;

public interface SalaryService {
  List<SalaryDto> getSalaries();
}
