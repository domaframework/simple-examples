package example.dao_style_basic.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import example.common.domain.Salary;
import example.common.entity.Employee;
import example.common.test.TestEnvironment;
import java.util.Arrays;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.doma.jdbc.Config;

@ExtendWith(TestEnvironment.class)
public class BatchUpdateTest {

  private final EmployeeDao dao;

  public BatchUpdateTest(Config config) {
    Objects.requireNonNull(config);
    this.dao = new EmployeeDaoImpl(config);
  }

  @Test
  public void testBatchUpdate() {
    Employee employee1 = dao.selectById(1);
    employee1.setSalary(employee1.getSalary().add(new Salary(100)));

    Employee employee2 = dao.selectById(2);
    employee2.setSalary(employee2.getSalary().add(new Salary(100)));

    int[] rows = dao.batchUpdate(Arrays.asList(employee1, employee2));
    assertEquals(2, rows.length);

    Employee employee3 = dao.selectById(1);
    Employee employee4 = dao.selectById(2);

    assertEquals(new Salary(0), employee3.getSalary().subtract(employee1.getSalary()));
    assertEquals(new Salary(0), employee4.getSalary().subtract(employee2.getSalary()));
  }
}
