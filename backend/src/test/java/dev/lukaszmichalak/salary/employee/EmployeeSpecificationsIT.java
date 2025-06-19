package dev.lukaszmichalak.salary.employee;

import static org.assertj.core.api.Assertions.assertThat;

import dev.lukaszmichalak.salary.employee.dto.EmployeeDto;
import dev.lukaszmichalak.salary.employee.dto.EmployeeSearchCriteria;
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
    EmployeeSearchCriteria criteria = new EmployeeSearchCriteria("Joe", null, null);

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
    EmployeeSearchCriteria criteria = new EmployeeSearchCriteria("joe", null, null);

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
    EmployeeSearchCriteria criteria = new EmployeeSearchCriteria("wal", null, null);

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
    EmployeeSearchCriteria criteria = new EmployeeSearchCriteria(null, "ACCOUNTANT", null);

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
    EmployeeSearchCriteria criteria = new EmployeeSearchCriteria(null, "accountant", null);

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
    EmployeeSearchCriteria criteria = new EmployeeSearchCriteria(null, "ACCOUN", null);

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
    EmployeeSearchCriteria criteria = new EmployeeSearchCriteria(null, null, "AGRICULTURE");

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
    EmployeeSearchCriteria criteria = new EmployeeSearchCriteria(null, null, "agriculture");

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
    EmployeeSearchCriteria criteria = new EmployeeSearchCriteria(null, null, "agricul");

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
    EmployeeSearchCriteria criteria =
        new EmployeeSearchCriteria("Joe", "ACCOUNTANT", "AGRICULTURE");

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
    EmployeeSearchCriteria criteria =
        new EmployeeSearchCriteria("NonExistentEmployeeName", null, null);

    // when
    List<EmployeeDto> result = employeeFacade.getEmployeesBySpecification(criteria);

    // then
    assertThat(result).isNotNull();
    assertThat(result).hasSize(0);
  }

  @Test
  void shouldReturnEmptyWhenNotFoundPositionTitle() {
    // given
    EmployeeSearchCriteria criteria =
        new EmployeeSearchCriteria(null, "NonExistentPositionTitle", null);

    // when
    List<EmployeeDto> result = employeeFacade.getEmployeesBySpecification(criteria);

    // then
    assertThat(result).isNotNull();
    assertThat(result).hasSize(0);
  }

  @Test
  void shouldReturnEmptyWhenNotFoundAgencyName() {
    // given
    EmployeeSearchCriteria criteria =
        new EmployeeSearchCriteria(null, null, "NonExistentAgencyName");

    // when
    List<EmployeeDto> result = employeeFacade.getEmployeesBySpecification(criteria);

    // then
    assertThat(result).isNotNull();
    assertThat(result).hasSize(0);
  }

  @Test
  void shouldReturnAllWhenNullCriteria() {
    // given
    EmployeeSearchCriteria criteria = new EmployeeSearchCriteria(null, null, null);

    // when
    List<EmployeeDto> result = employeeFacade.getEmployeesBySpecification(criteria);

    // then
    assertThat(result).isNotNull();
    assertThat(result).hasSize(5);
  }
}
