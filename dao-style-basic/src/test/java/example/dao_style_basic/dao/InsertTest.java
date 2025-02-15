package example.dao_style_basic.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import example.common.domain.Age;
import example.common.domain.Salary;
import example.common.entity.Employee;
import example.common.entity.JobType;
import example.common.test.TestEnvironment;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.doma.jdbc.Config;

@ExtendWith(TestEnvironment.class)
public class InsertTest {

  private final EmployeeDao dao;

  public InsertTest(Config config) {
    Objects.requireNonNull(config);
    this.dao = new EmployeeDaoImpl(config);
  }

  @Test
  public void testInsert() {
    Employee employee = new Employee();
    employee.setName("test");
    employee.setAge(new Age(50));
    employee.setSalary(new Salary(300));
    employee.setJobType(JobType.PRESIDENT);
    int rows = dao.insert(employee);
    assertEquals(1, rows);

    Employee employee2 = dao.selectById(employee.getId());
    assertEquals("test", employee2.getName());
    assertEquals(JobType.PRESIDENT, employee2.getJobType());
  }
}
