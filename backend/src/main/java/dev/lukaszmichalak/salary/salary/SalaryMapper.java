package dev.lukaszmichalak.salary.salary;

import dev.lukaszmichalak.salary.salary.dto.SalaryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface SalaryMapper {

  SalaryDto toDto(Salary salary);
}
