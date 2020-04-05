package examples;

import static org.junit.jupiter.api.Assertions.assertEquals;

import examples.dao.EmployeeDao;
import examples.dao.EmployeeDaoImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.doma.jdbc.tx.TransactionManager;

@ExtendWith(TestEnvironment.class)
public class DelegateTest {

  private final EmployeeDao dao = new EmployeeDaoImpl();

  @Test
  public void testDelegate() {
    TransactionManager tm = AppConfig.singleton().getTransactionManager();

    tm.required(
        () -> {
          assertEquals(14, dao.count());
        });
  }
}
