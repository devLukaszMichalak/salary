package dev.lukaszmichalak.salary.employee;

import static org.assertj.core.api.Assertions.assertThat;

import dev.lukaszmichalak.salary.employee.dto.EmployeeDto;
import dev.lukaszmichalak.salary.gateway.request.EmployeeQuery;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({"dev"})
class EmployeeSpecificationsIT {

  @Autowired private EmployeeFacade employeeFacade;

  @Test
  void shouldReturnByName() {
    // given
    EmployeeQuery criteria = new EmployeeQuery("Joe", null, null);

    // when
    List<EmployeeDto> result = employeeFacade.getEmployeesBySpecification(criteria);

    // then
    assertThat(result).isNotNull();
    assertThat(result).hasSize(1);
    assertThat(result.getFirst().name()).isEqualTo("WALKER, JOE E.");
  }

  @Test
  void shouldReturnByNameCaseInsensitive() {
    // given
    EmployeeQuery criteria = new EmployeeQuery("joe", null, null);

    // when
    List<EmployeeDto> result = employeeFacade.getEmployeesBySpecification(criteria);

    // then
    assertThat(result).isNotNull();
    assertThat(result).hasSize(1);
    assertThat(result.getFirst().name()).isEqualTo("WALKER, JOE E.");
  }

  @Test
  void shouldReturnByPartialName() {
    // given
    EmployeeQuery criteria = new EmployeeQuery("wal", null, null);

    // when
    List<EmployeeDto> result = employeeFacade.getEmployeesBySpecification(criteria);

    // then
    assertThat(result).isNotNull();
    assertThat(result).hasSize(1);
    assertThat(result.getFirst().name()).isEqualTo("WALKER, JOE E.");
  }

  @Test
  void shouldReturnByPositionTitle() {
    // given
    EmployeeQuery criteria = new EmployeeQuery(null, "ACCOUNTANT", null);

    // when
    List<EmployeeDto> result = employeeFacade.getEmployeesBySpecification(criteria);

    // then
    assertThat(result).isNotNull();
    assertThat(result).hasSizeGreaterThan(1);
    assertThat(result.getFirst().positionTitle()).startsWith("ACCOUNTANT");
  }

  @Test
  void shouldReturnByPositionTitleCaseInsensitive() {
    // given
    EmployeeQuery criteria = new EmployeeQuery(null, "accountant", null);

    // when
    List<EmployeeDto> result = employeeFacade.getEmployeesBySpecification(criteria);

    // then
    assertThat(result).isNotNull();
    assertThat(result).hasSizeGreaterThan(1);
    assertThat(result.getFirst().positionTitle()).startsWith("ACCOUNTANT");
  }

  @Test
  void shouldReturnByPartialPositionTitle() {
    // given
    EmployeeQuery criteria = new EmployeeQuery(null, "ACCOUN", null);

    // when
    List<EmployeeDto> result = employeeFacade.getEmployeesBySpecification(criteria);

    // then
    assertThat(result).isNotNull();
    assertThat(result).hasSizeGreaterThan(1);
    assertThat(result.getFirst().positionTitle()).startsWith("ACCOUNTANT");
  }

  @Test
  void shouldReturnByAgencyName() {
    // given
    EmployeeQuery criteria = new EmployeeQuery(null, null, "AGRICULTURE");

    // when
    List<EmployeeDto> result = employeeFacade.getEmployeesBySpecification(criteria);

    // then
    assertThat(result).isNotNull();
    assertThat(result).hasSizeGreaterThan(1);
    assertThat(result.getFirst().agencyName()).isEqualTo("AGRICULTURE");
  }

  @Test
  void shouldReturnByAgencyNameCaseInsensitive() {
    // given
    EmployeeQuery criteria = new EmployeeQuery(null, null, "agriculture");

    // when
    List<EmployeeDto> result = employeeFacade.getEmployeesBySpecification(criteria);

    // then
    assertThat(result).isNotNull();
    assertThat(result).hasSizeGreaterThan(1);
    assertThat(result.getFirst().agencyName()).isEqualTo("AGRICULTURE");
  }

  @Test
  void shouldReturnByPartialAgencyName() {
    // given
    EmployeeQuery criteria = new EmployeeQuery(null, null, "agricul");

    // when
    List<EmployeeDto> result = employeeFacade.getEmployeesBySpecification(criteria);

    // then
    assertThat(result).isNotNull();
    assertThat(result).hasSizeGreaterThan(1);
    assertThat(result.getFirst().agencyName()).isEqualTo("AGRICULTURE");
  }

  @Test
  void shouldReturnByMultipleCriteria() {
    // given
    EmployeeQuery criteria = new EmployeeQuery("Joe", "ACCOUNTANT", "AGRICULTURE");

    // when
    List<EmployeeDto> result = employeeFacade.getEmployeesBySpecification(criteria);

    // then
    assertThat(result).isNotNull();
    assertThat(result).hasSize(1);
    EmployeeDto first = result.getFirst();
    assertThat(first.name()).isEqualTo("WALKER, JOE E.");
    assertThat(first.positionTitle()).startsWith("ACCOUNTANT");
    assertThat(first.agencyName()).isEqualTo("AGRICULTURE");
  }

  @Test
  void shouldReturnEmptyWhenNotFoundName() {
    // given
    EmployeeQuery criteria = new EmployeeQuery("NonExistentEmployeeName", null, null);

    // when
    List<EmployeeDto> result = employeeFacade.getEmployeesBySpecification(criteria);

    // then
    assertThat(result).isNotNull();
    assertThat(result).hasSize(0);
  }

  @Test
  void shouldReturnEmptyWhenNotFoundPositionTitle() {
    // given
    EmployeeQuery criteria = new EmployeeQuery(null, "NonExistentPositionTitle", null);

    // when
    List<EmployeeDto> result = employeeFacade.getEmployeesBySpecification(criteria);

    // then
    assertThat(result).isNotNull();
    assertThat(result).hasSize(0);
  }

  @Test
  void shouldReturnEmptyWhenNotFoundAgencyName() {
    // given
    EmployeeQuery criteria = new EmployeeQuery(null, null, "NonExistentAgencyName");

    // when
    List<EmployeeDto> result = employeeFacade.getEmployeesBySpecification(criteria);

    // then
    assertThat(result).isNotNull();
    assertThat(result).hasSize(0);
  }

  @Test
  void shouldReturnAllWhenNullCriteria() {
    // given
    EmployeeQuery criteria = new EmployeeQuery(null, null, null);

    // when
    List<EmployeeDto> result = employeeFacade.getEmployeesBySpecification(criteria);

    // then
    assertThat(result).isNotNull();
    assertThat(result).hasSize(5);
  }
}
