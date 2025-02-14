package example.dao_style_basic.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import example.common.entity.Employee;
import example.common.entity.JobType;
import example.common.test.TestEnvironment;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.doma.jdbc.Config;

@ExtendWith(TestEnvironment.class)
public class UpdateTest {

  private final EmployeeDao dao;

  public UpdateTest(Config config) {
    Objects.requireNonNull(config);
    this.dao = new EmployeeDaoImpl(config);
  }

  @Test
  public void testUpdate() {
    Employee employee = dao.selectById(1);
    employee.setName("hoge");
    employee.setJobType(JobType.PRESIDENT);
    int rows = dao.update(employee);
    assertEquals(1, rows);

    Employee employee2 = dao.selectById(1);
    assertEquals("hoge", employee2.getName());
    assertEquals(JobType.PRESIDENT, employee2.getJobType());
  }
}
