package examples;

import examples.dao.EmployeeDao;
import examples.dao.EmployeeDaoImpl;
import examples.entity.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.doma.jdbc.tx.TransactionManager;

import java.util.List;

@ExtendWith(TestEnvironment.class)
public class BatchDeleteTest {

  private final EmployeeDao dao = new EmployeeDaoImpl();

  @Test
  public void testBatchDelete() {
    TransactionManager tm = AppConfig.singleton().getTransactionManager();

    tm.required(
        () -> {
          List<Employee> list = dao.selectAll();
          dao.batchDelete(list);
        });
  }
}
