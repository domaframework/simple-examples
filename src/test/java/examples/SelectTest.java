package examples;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import examples.dao.EmployeeDao;
import examples.dao.EmployeeDaoImpl;
import examples.domain.Age;
import examples.domain.Salary;
import examples.entity.Employee;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TestEnvironment.class)
public class SelectTest {

  private final DbConfig config;
  private final EmployeeDao dao;

  SelectTest(DbConfig config) {
    this.config = config;
    dao = new EmployeeDaoImpl(config);
  }

  @Test
  public void testSimpleSelect() {
    var tm = config.getTransactionManager();

    tm.required(
        () -> {
          var employee = dao.selectById(1);
          assertNotNull(employee);
        });
  }

  @Test
  public void testConditionalSelect() {
    var tm = config.getTransactionManager();

    tm.required(
        () -> {
          var list = dao.selectByAgeRange(new Age(30), new Age(40));
          assertEquals(6, list.size());
          list = dao.selectByAgeRange(new Age(30), null);
          assertEquals(12, list.size());
          list = dao.selectByAgeRange(null, new Age(40));
          assertEquals(8, list.size());
          list = dao.selectByAgeRange(null, null);
          assertEquals(14, list.size());
        });
  }

  @Test
  public void testConditionalSelect2() {
    var tm = config.getTransactionManager();

    tm.required(
        () -> {
          var list = dao.selectByName("SMITH");
          assertEquals(1, list.size());
          list = dao.selectByName(null);
          assertEquals(0, list.size());
        });
  }

  @Test
  public void testIsNotEmpty() {
    var tm = config.getTransactionManager();

    tm.required(
        () -> {
          var list = dao.selectByNotEmptyName("SMITH");
          assertEquals(1, list.size());
          list = dao.selectByNotEmptyName(null);
          assertEquals(14, list.size());
          list = dao.selectByNotEmptyName("");
          assertEquals(14, list.size());
          list = dao.selectByNotEmptyName("    ");
          assertEquals(0, list.size());
        });
  }

  @Test
  public void testLikePredicate_prefix() {
    var tm = config.getTransactionManager();

    tm.required(
        () -> {
          var list = dao.selectByNameWithPrefixMatching("S");
          assertEquals(2, list.size());
        });
  }

  @Test
  public void testLikePredicate_suffix() {
    var tm = config.getTransactionManager();

    tm.required(
        () -> {
          var list = dao.selectByNameWithSuffixMatching("S");
          assertEquals(3, list.size());
        });
  }

  @Test
  public void testLikePredicate_inside() {
    var tm = config.getTransactionManager();

    tm.required(
        () -> {
          var list = dao.selectByNameWithInfixMatching("A");
          assertEquals(7, list.size());
        });
  }

  @Test
  public void testInPredicate() {
    var tm = config.getTransactionManager();

    tm.required(
        () -> {
          var names = Arrays.asList("JONES", "SCOTT", "XXX");
          var list = dao.selectByNames(names);
          assertEquals(2, list.size());
        });
  }

  @Test
  public void testInPredicate_Domain() {
    var tm = config.getTransactionManager();

    tm.required(
        () -> {
          var ages = Stream.of(30, 40, 50, 60).map(Age::new).collect(toList());
          var list = dao.selectByAges(ages);
          assertEquals(3, list.size());
        });
  }

  @Test
  public void testSelectByTimestampRange() {
    var tm = config.getTransactionManager();

    tm.required(
        () -> {
          var from = LocalDateTime.parse("2008-01-20T12:34:56");
          var to = LocalDateTime.parse("2008-03-20T12:34:56");

          var list = dao.selectByHiredateRange(from, to);
          assertEquals(3, list.size());
        });
  }

  @Test
  public void testSelectByDomain() {
    var tm = config.getTransactionManager();

    tm.required(
        () -> {
          var list = dao.selectBySalary(new Salary(2900));
          assertEquals(4, list.size());
        });
  }

  @Test
  public void testSelectDomain() {
    var tm = config.getTransactionManager();

    tm.required(
        () -> {
          var salary = dao.selectSummedSalary();
          assertTrue(salary.isPresent());
        });
  }

  @Test
  public void testSelectByEntity() {
    var tm = config.getTransactionManager();

    tm.required(
        () -> {
          var e = new Employee();
          e.setName("SMITH");
          var list = dao.selectByExample(e);
          assertEquals(1, list.size());
        });
  }

  @Test
  public void testStream() {
    var tm = config.getTransactionManager();
    tm.required(
        () -> {
          var sum =
              dao.selectByAge(
                  new Age(30),
                  s ->
                      s.map(Employee::getSalary)
                          .filter(Objects::nonNull)
                          .reduce(new Salary(0), Salary::add));
          assertEquals(Integer.valueOf(21975), sum.getValue());
        });
  }

  @Test
  public void testOffsetLimit() {
    var tm = config.getTransactionManager();

    tm.required(
        () -> {
          var list = dao.select(5, 3);
          assertEquals(3, list.size());
        });
  }

  @Test
  public void testCount() {
    var tm = config.getTransactionManager();

    tm.required(
        () -> {
          var tuple2 = dao.selectAndCount(5, 3);
          var list = tuple2.getItem1();
          var count = tuple2.getItem2();
          assertEquals(3, list.size());
          assertEquals(14, count);
        });
  }

  @Test
  public void testAssociation() {
    var tm = config.getTransactionManager();

    tm.required(
        () -> {
          var list = dao.selectAllWithAssociation();
          assertEquals(14, list.size());
          for (var e : list) {
            assertNotNull(e.getDepartment().getName());
          }
        });
  }
}
