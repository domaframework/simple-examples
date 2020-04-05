package examples;

import examples.dao.EmployeeDao;
import examples.dao.EmployeeDaoImpl;
import examples.entity.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.doma.jdbc.tx.TransactionManager;

@ExtendWith(TestEnvironment.class)
public class DeleteTest {

  private final EmployeeDao dao = new EmployeeDaoImpl();

  @Test
  public void testDelete() {
    TransactionManager tm = AppConfig.singleton().getTransactionManager();

    tm.required(
        () -> {
          Employee employee = dao.selectById(1);
          dao.delete(employee);
        });
  }

  @Test
  public void testDeleteWithSqlFile() {
    TransactionManager tm = AppConfig.singleton().getTransactionManager();

    tm.required(
        () -> {
          Employee employee = dao.selectById(1);
          dao.deleteWithSqlFile(employee);
        });
  }
}
