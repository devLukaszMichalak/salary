package dev.lukaszmichalak.salary.salary;

import dev.lukaszmichalak.salary.salary.dto.SalaryDto;

class SalaryMapper {

  static SalaryDto map(Salary salary) {
    return new SalaryDto(
        salary.getId(), salary.getYear(), salary.getYearlyGrossPay(), salary.getEmployeeId());
  }
}
