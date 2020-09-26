package example.sql_file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import example.sql_file.dao.EmployeeDao;
import example.sql_file.dao.EmployeeDaoImpl;
import example.sql_file.domain.Salary;
import example.sql_file.entity.Employee;
import example.sql_file.entity.EmployeeDepartment;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.SelectOptions;

@ExtendWith(TestEnvironment.class)
public class SelectTest {

  private final EmployeeDao dao;

  public SelectTest(Config config) {
    Objects.requireNonNull(config);
    this.dao = new EmployeeDaoImpl(config);
  }

  @Test
  public void testSelectAll() {
    List<Employee> employees = dao.selectAll();
    assertEquals(14, employees.size());
  }

  @Test
  public void testSelectById() {
    Employee employee = dao.selectById(1);
    assertNotNull(employee);
  }

  @Test
  public void testConditionalSelect() {
    List<Employee> list = dao.selectByAgeRange(30, 40);
    assertEquals(6, list.size());
    list = dao.selectByAgeRange(30, null);
    assertEquals(12, list.size());
    list = dao.selectByAgeRange(null, 40);
    assertEquals(8, list.size());
    list = dao.selectByAgeRange(null, null);
    assertEquals(14, list.size());
  }

  @Test
  public void testConditionalSelect2() {
    List<Employee> list = dao.selectByName("SMITH");
    assertEquals(1, list.size());
    list = dao.selectByName(null);
    assertEquals(0, list.size());
  }

  @Test
  public void testLoopSelect() {
    List<Integer> ages = Arrays.asList(30, 40, 50, 60);
    List<Employee> list = dao.selectByAges(ages);
    assertEquals(3, list.size());
  }

  @Test
  public void testIsNotEmptyFunction() {
    List<Employee> list = dao.selectByNotEmptyName("SMITH");
    assertEquals(1, list.size());
    list = dao.selectByNotEmptyName(null);
    assertEquals(14, list.size());
    list = dao.selectByNotEmptyName("");
    assertEquals(14, list.size());
    list = dao.selectByNotEmptyName("    ");
    assertEquals(0, list.size());
  }

  @Test
  public void testLikePredicate_prefix() {
    List<Employee> list = dao.selectByNameWithPrefixMatching("S");
    assertEquals(2, list.size());
  }

  @Test
  public void testLikePredicate_suffix() {
    List<Employee> list = dao.selectByNameWithSuffixMatching("S");
    assertEquals(3, list.size());
  }

  @Test
  public void testLikePredicate_inside() {
    List<Employee> list = dao.selectByNameWithInfixMatching("A");
    assertEquals(7, list.size());
  }

  @Test
  public void testInPredicate() {
    List<String> names = Arrays.asList("JONES", "SCOTT", "XXX");
    List<Employee> list = dao.selectByNames(names);
    assertEquals(2, list.size());
  }

  @Test
  public void testSelectByTimestampRange() {
    Timestamp from = Timestamp.valueOf("2008-01-20 12:34:56");
    Timestamp to = Timestamp.valueOf("2008-03-20 12:34:56");
    List<Employee> list = dao.selectByHiredateRange(from, to);
    assertEquals(3, list.size());
  }

  @Test
  public void testSelectByDomain() {
    List<Employee> list = dao.selectBySalary(new Salary(2900));
    assertEquals(4, list.size());
  }

  @Test
  public void testSelectDomain() {
    Salary salary = dao.selectSummedSalary();
    assertNotNull(salary);
  }

  @Test
  public void testSelectByEntity() {
    Employee e = new Employee();
    e.setName("SMITH");
    List<Employee> list = dao.selectByExample(e);
    assertEquals(1, list.size());
  }

  @Test
  public void testStream() {
    Salary sum =
        dao.selectByAge(
            30,
            s ->
                s.map(Employee::getSalary)
                    .filter(Objects::nonNull)
                    .reduce(new Salary(0), Salary::add));
    assertEquals(Integer.valueOf(21975), sum.getValue());
  }

  @Test
  public void testOffsetLimit() {
    SelectOptions options = SelectOptions.get().offset(5).limit(3);
    List<Employee> list = dao.selectAll(options);
    assertEquals(3, list.size());
  }

  @Test
  public void testCount() {
    SelectOptions options = SelectOptions.get().offset(5).limit(3).count();
    List<Employee> list = dao.selectAll(options);
    assertEquals(3, list.size());
    assertEquals(14, options.getCount());
  }

  @Test
  public void testSelectJoinedResult() {
    List<EmployeeDepartment> list = dao.selectAllEmployeeDepartment();
    assertEquals(14, list.size());
    for (EmployeeDepartment e : list) {
      assertNotNull(e.getDepartmentName());
    }
  }
}
