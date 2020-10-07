package example.dsl_style_java;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import example.dsl_style_java.domain.Age;
import example.dsl_style_java.domain.Salary;
import example.dsl_style_java.entity.Employee;
import example.dsl_style_java.entity.NameAndSalaryDto;
import example.dsl_style_java.repository.EmployeeRepository;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.criteria.tuple.Tuple2;

@ExtendWith(TestEnvironment.class)
public class SelectTest {

  private final EmployeeRepository repository;

  SelectTest(Config config) {
    repository = new EmployeeRepository(config);
  }

  @Test
  public void testSimpleSelect() {
    Employee employee = repository.selectById(1);
    assertNotNull(employee);
  }

  @Test
  public void testConditionalSelect() {
    List<Employee> list = repository.selectByAgeRange(new Age(30), new Age(40));
    assertEquals(6, list.size());
    list = repository.selectByAgeRange(new Age(30), null);
    assertEquals(12, list.size());
    list = repository.selectByAgeRange(null, new Age(40));
    assertEquals(8, list.size());
    list = repository.selectByAgeRange(null, null);
    assertEquals(14, list.size());
  }

  @Test
  public void testConditionalSelect2() {
    List<Employee> list = repository.selectByName("SMITH");
    assertEquals(1, list.size());
    list = repository.selectByName(null);
    assertEquals(14, list.size());
  }

  @Test
  public void testIsNotEmpty() {
    List<Employee> list = repository.selectByNotEmptyName("SMITH");
    assertEquals(1, list.size());
    list = repository.selectByNotEmptyName(null);
    assertEquals(14, list.size());
    list = repository.selectByNotEmptyName("");
    assertEquals(14, list.size());
    list = repository.selectByNotEmptyName("    ");
    assertEquals(0, list.size());
  }

  @Test
  public void testLikePredicate_prefix() {
    List<Employee> list = repository.selectByNameWithPrefixMatching("S");
    assertEquals(2, list.size());
  }

  @Test
  public void testLikePredicate_suffix() {
    List<Employee> list = repository.selectByNameWithSuffixMatching("S");
    assertEquals(3, list.size());
  }

  @Test
  public void testLikePredicate_inside() {
    List<Employee> list = repository.selectByNameWithInfixMatching("A");
    assertEquals(7, list.size());
  }

  @Test
  public void testInPredicate() {
    List<String> names = Arrays.asList("JONES", "SCOTT", "XXX");
    List<Employee> list = repository.selectByNames(names);
    assertEquals(2, list.size());
  }

  @Test
  public void testInPredicate_Domain() {
    List<Age> ages = Stream.of(30, 40, 50, 60).map(Age::new).collect(toList());
    List<Employee> list = repository.selectByAges(ages);
    assertEquals(3, list.size());
  }

  @Test
  public void testSelectByTimestampRange() {
    LocalDateTime from = LocalDateTime.parse("2008-01-20T12:34:56");
    LocalDateTime to = LocalDateTime.parse("2008-03-20T12:34:56");

    List<Employee> list = repository.selectByHiredateRange(from, to);
    assertEquals(3, list.size());
  }

  @Test
  public void testSelectByDomain() {
    List<Employee> list = repository.selectBySalary(new Salary(2900));
    assertEquals(4, list.size());
  }

  @Test
  public void testSelectDomain() {
    Optional<Salary> salary = repository.selectSummedSalary();
    assertTrue(salary.isPresent());
  }

  @Test
  public void testSelectByEntity() {
    Employee e = new Employee();
    e.setName("SMITH");
    List<Employee> list = repository.selectByExample(e);
    assertEquals(1, list.size());
  }

  @Test
  public void testStream() {
    Salary sum =
        repository.selectByAge(
            new Age(30),
            s ->
                s.map(Employee::getSalary)
                    .filter(Objects::nonNull)
                    .reduce(new Salary(0), Salary::add));
    assertEquals(Integer.valueOf(21975), sum.getValue());
  }

  @Test
  public void testOffsetLimit() {
    List<Employee> list = repository.select(5, 3);
    assertEquals(3, list.size());
  }

  @Test
  public void testCount() {
    Tuple2<List<Employee>, Long> tuple2 = repository.selectAndCount(5, 3);
    List<Employee> list = tuple2.getItem1();
    long count = tuple2.getItem2();
    assertEquals(3, list.size());
    assertEquals(14, count);
  }

  @Test
  public void testSelectByDepartmentName_in() {
    List<Employee> list = repository.selectByDepartmentName_in("SALES");
    assertEquals(4, list.size());
  }

  @Test
  public void testSelectByDepartmentName_exists() {
    List<Employee> list = repository.selectByDepartmentName_exists("SALES");
    assertEquals(4, list.size());
  }

  @Test
  public void testSelectByDepartmentName_join() {
    List<Employee> list = repository.selectByDepartmentName_join("SALES");
    assertEquals(4, list.size());
  }

  @Test
  public void testAssociation() {
    List<Employee> list = repository.selectAllWithAssociation();
    assertEquals(14, list.size());
    for (Employee e : list) {
      assertNotNull(e.getDepartment().getName());
      assertTrue(e.getDepartment().getEmployees().contains(e));
    }
  }

  @Test
  public void testSelectNameAndSalaryAsTuple2List() {
    List<Tuple2<String, Salary>> list = repository.selectNameAndSalaryAsTuple2List();
    assertEquals(14, list.size());
    for (Tuple2<String, Salary> tuple2 : list) {
      assertNotNull(tuple2.getItem1());
      assertNotNull(tuple2.getItem2());
    }
  }

  @Test
  public void testSelectNameAndSalaryAsNameAndSalaryDtoList() {
    List<NameAndSalaryDto> list = repository.selectNameAndSalaryAsNameAndSalaryDtoList();
    assertEquals(14, list.size());
    for (NameAndSalaryDto dto : list) {
      assertNotNull(dto.getName());
      assertNotNull(dto.getSalary());
    }
  }

  @Test
  public void testSelectNameAndSalaryAsEntityList() {
    List<Employee> list = repository.selectNameAndSalaryAsEntityList();
    assertEquals(14, list.size());
    for (Employee employee : list) {
      assertNotNull(employee.getId());
      assertNotNull(employee.getName());
      assertNotNull(employee.getSalary());
    }
  }
}
