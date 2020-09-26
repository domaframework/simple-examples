package example.dao_style_basic.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import example.dao_style_basic.domain.Age;
import example.dao_style_basic.domain.Salary;
import example.dao_style_basic.entity.Employee;
import java.util.Arrays;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.doma.jdbc.Config;

@ExtendWith(TestEnvironment.class)
public class BatchInsertTest {

  private final EmployeeDao dao;

  public BatchInsertTest(Config config) {
    Objects.requireNonNull(config);
    this.dao = new EmployeeDaoImpl(config);
  }

  @Test
  public void testBatchInsert() {
    Employee employee1 = new Employee();
    employee1.setName("test-1");
    employee1.setAge(new Age(30));
    employee1.setSalary(new Salary(300));

    Employee employee2 = new Employee();
    employee2.setName("test-2");
    employee2.setAge(new Age(40));
    employee2.setSalary(new Salary(500));

    int[] rows = dao.batchInsert(Arrays.asList(employee1, employee2));
    assertEquals(2, rows.length);

    assertNotNull(dao.selectById(employee1.getId()));
    assertNotNull(dao.selectById(employee2.getId()));
  }
}
