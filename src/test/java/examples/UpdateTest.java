package examples;

import examples.dao.EmployeeDao;
import examples.dao.EmployeeDaoImpl;
import examples.entity.JobType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TestEnvironment.class)
public class UpdateTest {

  private final DbConfig config;
  private final EmployeeDao dao;

  UpdateTest(DbConfig config) {
    this.config = config;
    dao = new EmployeeDaoImpl(config);
  }

  @Test
  public void testUpdate() {
    var tm = config.getTransactionManager();

    tm.required(
        () -> {
          var optional = dao.selectById(1);
          var employee = optional.orElseThrow();
          employee.setName("hoge");
          employee.setJobType(JobType.PRESIDENT);
          dao.update(employee);
        });
  }

  @Test
  public void testUpdateByNativeSql() {
    var tm = config.getTransactionManager();

    tm.required(
        () -> {
          var optional = dao.selectById(1);
          var employee = optional.orElseThrow();
          employee.setName("hoge");
          employee.setJobType(JobType.PRESIDENT);
          dao.updateByNativeSql(employee);
        });
  }
}
