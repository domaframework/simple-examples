package examples;

import examples.dao.EmployeeDao;
import examples.dao.EmployeeDaoImpl;
import examples.entity.Employee;
import examples.entity.JobType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.doma.jdbc.tx.TransactionManager;

@ExtendWith(TestEnvironment.class)
public class UpdateTest {

  private final EmployeeDao dao = new EmployeeDaoImpl();

  @Test
  public void testUpdate() {
    TransactionManager tm = AppConfig.singleton().getTransactionManager();

    tm.required(
        () -> {
          Employee employee = dao.selectById(1);
          employee.setName("hoge");
          employee.setJobType(JobType.PRESIDENT);
          dao.update(employee);
        });
  }

  @Test
  public void testUpdateWithSqlFile() {
    TransactionManager tm = AppConfig.singleton().getTransactionManager();

    tm.required(
        () -> {
          Employee employee = dao.selectById(1);
          employee.setName("hoge");
          employee.setJobType(JobType.PRESIDENT);
          dao.updateWithSqlFile(employee);
        });
  }
}
