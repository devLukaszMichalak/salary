package dev.lukaszmichalak.salary.employee;

import dev.lukaszmichalak.salary.employee.dto.EmployeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
interface EmployeeMapper {

  @Mapping(target = "positionTitle", source = "position.title")
  @Mapping(target = "agencyName", source = "agency.name")
  EmployeeDto toDto(Employee employee);
}
