package example.dao_style_text;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import example.dao_style_text.dao.EmployeeDao;
import example.dao_style_text.dao.EmployeeDaoImpl;
import example.dao_style_text.domain.Salary;
import example.dao_style_text.entity.Employee;
import java.sql.Timestamp;
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
    var employees = dao.selectAll();
    assertEquals(14, employees.size());
  }

  @Test
  public void testSelectById() {
    var employee = dao.selectById(1);
    assertNotNull(employee);
  }

  @Test
  public void testConditionalSelect() {
    var list = dao.selectByAgeRange(30, 40);
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
    var list = dao.selectByName("SMITH");
    assertEquals(1, list.size());
    list = dao.selectByName(null);
    assertEquals(0, list.size());
  }

  @Test
  public void testLoopSelect() {
    var ages = List.of(30, 40, 50, 60);
    var list = dao.selectByAges(ages);
    assertEquals(3, list.size());
  }

  @Test
  public void testIsNotEmptyFunction() {
    var list = dao.selectByNotEmptyName("SMITH");
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
    var list = dao.selectByNameWithPrefixMatching("S");
    assertEquals(2, list.size());
  }

  @Test
  public void testLikePredicate_suffix() {
    var list = dao.selectByNameWithSuffixMatching("S");
    assertEquals(3, list.size());
  }

  @Test
  public void testLikePredicate_inside() {
    var list = dao.selectByNameWithInfixMatching("A");
    assertEquals(7, list.size());
  }

  @Test
  public void testInPredicate() {
    var names = List.of("JONES", "SCOTT", "XXX");
    var list = dao.selectByNames(names);
    assertEquals(2, list.size());
  }

  @Test
  public void testSelectByTimestampRange() {
    var from = Timestamp.valueOf("2008-01-20 12:34:56");
    var to = Timestamp.valueOf("2008-03-20 12:34:56");
    var list = dao.selectByHiredateRange(from, to);
    assertEquals(3, list.size());
  }

  @Test
  public void testSelectByDomain() {
    var list = dao.selectBySalary(new Salary(2900));
    assertEquals(4, list.size());
  }

  @Test
  public void testSelectDomain() {
    var salary = dao.selectSummedSalary();
    assertNotNull(salary);
  }

  @Test
  public void testSelectByEntity() {
    var e = new Employee();
    e.setName("SMITH");
    var list = dao.selectByExample(e);
    assertEquals(1, list.size());
  }

  @Test
  public void testStream() {
    var sum =
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
    var options = SelectOptions.get().offset(5).limit(3);
    var list = dao.selectAll(options);
    assertEquals(3, list.size());
  }

  @Test
  public void testCount() {
    var options = SelectOptions.get().offset(5).limit(3).count();
    var list = dao.selectAll(options);
    assertEquals(3, list.size());
    assertEquals(14, options.getCount());
  }

  @Test
  public void testSelectJoinedResult() {
    var list = dao.selectAllEmployeeDepartment();
    assertEquals(14, list.size());
    for (var e : list) {
      assertNotNull(e.getDepartmentName());
    }
  }
}
