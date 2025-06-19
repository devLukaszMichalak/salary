package dev.lukaszmichalak.salary.employee;

import static org.assertj.core.api.Assertions.assertThat;

import dev.lukaszmichalak.salary.employee.dto.EmployeeDto;
import dev.lukaszmichalak.salary.employee.dto.EmployeeSearchCriteria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({"dev"})
class EmployeeFacadePageableIT {

  @Autowired
  private EmployeeFacade employeeFacade;

  @Test
  void shouldReturnPageableResults() {
    // given
    EmployeeSearchCriteria criteria = new EmployeeSearchCriteria("Joe", null, null);
    Pageable pageable = PageRequest.of(0, 10);

    // when
    Page<EmployeeDto> result = employeeFacade.getEmployeesBySpecification(criteria, pageable);

    // then
    assertThat(result).isNotNull();
    assertThat(result.getContent()).isNotNull();
    assertThat(result.getTotalElements()).isGreaterThan(0);
    assertThat(result.getTotalPages()).isGreaterThan(0);
    assertThat(result.getSize()).isEqualTo(10);
    assertThat(result.getNumber()).isEqualTo(0);
  }

  @Test
  void shouldReturnEmptyPageWhenNoResults() {
    // given
    EmployeeSearchCriteria criteria = new EmployeeSearchCriteria("NonExistentEmployeeName", null, null);
    Pageable pageable = PageRequest.of(0, 10);

    // when
    Page<EmployeeDto> result = employeeFacade.getEmployeesBySpecification(criteria, pageable);

    // then
    assertThat(result).isNotNull();
    assertThat(result.getContent()).isEmpty();
    assertThat(result.getTotalElements()).isEqualTo(0);
    assertThat(result.getTotalPages()).isEqualTo(0);
  }

  @Test
  void shouldHandleNullCriteria() {
    // given
    EmployeeSearchCriteria criteria = new EmployeeSearchCriteria(null, null, null);
    Pageable pageable = PageRequest.of(0, 10);

    // when
    Page<EmployeeDto> result = employeeFacade.getEmployeesBySpecification(criteria, pageable);

    // then
    assertThat(result).isNotNull();
    assertThat(result.getContent()).isNotNull();
  }
} 