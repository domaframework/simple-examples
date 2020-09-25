package example.criteria_api;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import example.criteria_api.domain.Age;
import example.criteria_api.domain.Salary;
import example.criteria_api.entity.Employee;
import example.criteria_api.repository.EmployeeRepository;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TestEnvironment.class)
public class SelectTest {

  private final EmployeeRepository repository;

  SelectTest(DbConfig config) {
    repository = new EmployeeRepository(config);
  }

  @Test
  public void testSimpleSelect() {
    var employee = repository.selectById(1);
    assertNotNull(employee);
  }

  @Test
  public void testConditionalSelect() {
    var list = repository.selectByAgeRange(new Age(30), new Age(40));
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
    var list = repository.selectByName("SMITH");
    assertEquals(1, list.size());
    list = repository.selectByName(null);
    assertEquals(14, list.size());
  }

  @Test
  public void testIsNotEmpty() {
    var list = repository.selectByNotEmptyName("SMITH");
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
    var list = repository.selectByNameWithPrefixMatching("S");
    assertEquals(2, list.size());
  }

  @Test
  public void testLikePredicate_suffix() {
    var list = repository.selectByNameWithSuffixMatching("S");
    assertEquals(3, list.size());
  }

  @Test
  public void testLikePredicate_inside() {
    var list = repository.selectByNameWithInfixMatching("A");
    assertEquals(7, list.size());
  }

  @Test
  public void testInPredicate() {
    var names = Arrays.asList("JONES", "SCOTT", "XXX");
    var list = repository.selectByNames(names);
    assertEquals(2, list.size());
  }

  @Test
  public void testInPredicate_Domain() {
    var ages = Stream.of(30, 40, 50, 60).map(Age::new).collect(toList());
    var list = repository.selectByAges(ages);
    assertEquals(3, list.size());
  }

  @Test
  public void testSelectByTimestampRange() {
    var from = LocalDateTime.parse("2008-01-20T12:34:56");
    var to = LocalDateTime.parse("2008-03-20T12:34:56");

    var list = repository.selectByHiredateRange(from, to);
    assertEquals(3, list.size());
  }

  @Test
  public void testSelectByDomain() {
    var list = repository.selectBySalary(new Salary(2900));
    assertEquals(4, list.size());
  }

  @Test
  public void testSelectDomain() {
    var salary = repository.selectSummedSalary();
    assertTrue(salary.isPresent());
  }

  @Test
  public void testSelectByEntity() {
    var e = new Employee();
    e.setName("SMITH");
    var list = repository.selectByExample(e);
    assertEquals(1, list.size());
  }

  @Test
  public void testStream() {
    var sum =
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
    var list = repository.select(5, 3);
    assertEquals(3, list.size());
  }

  @Test
  public void testCount() {
    var tuple2 = repository.selectAndCount(5, 3);
    var list = tuple2.getItem1();
    var count = tuple2.getItem2();
    assertEquals(3, list.size());
    assertEquals(14, count);
  }

  @Test
  public void testAssociation() {
    var list = repository.selectAllWithAssociation();
    assertEquals(14, list.size());
    for (var e : list) {
      assertNotNull(e.getDepartment().getName());
    }
  }
}
