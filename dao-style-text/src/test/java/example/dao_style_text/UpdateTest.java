package example.dao_style_text;

import static org.junit.jupiter.api.Assertions.assertEquals;

import example.dao_style_text.dao.EmployeeDao;
import example.dao_style_text.dao.EmployeeDaoImpl;
import example.dao_style_text.entity.JobType;
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
    var employee = dao.selectById(1);
    employee.setName("hoge");
    employee.setJobType(JobType.PRESIDENT);
    var rows = dao.update(employee);
    assertEquals(1, rows);
  }
}
