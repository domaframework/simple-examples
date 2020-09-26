package example.sql_annotation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import example.sql_annotation.dao.EmployeeDao;
import example.sql_annotation.dao.EmployeeDaoImpl;
import example.sql_annotation.entity.JobType;
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
