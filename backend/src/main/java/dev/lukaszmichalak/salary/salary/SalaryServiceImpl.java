package dev.lukaszmichalak.salary.salary;

import dev.lukaszmichalak.salary.salary.dto.SalaryDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class SalaryServiceImpl implements SalaryService {

  private final SalaryRepository salaryRepository;

  @Override
  public List<SalaryDto> getSalaries() {
    return salaryRepository.findAll().stream().map(SalaryMapper::map).toList();
  }
}
