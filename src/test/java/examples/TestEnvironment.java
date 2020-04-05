package examples;

import examples.dao.EmployeeDao;
import examples.dao.EmployeeDaoImpl;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.seasar.doma.jdbc.tx.TransactionManager;

public class TestEnvironment implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

  private EmployeeDao dao = new EmployeeDaoImpl();

  @Override
  public void beforeTestExecution(ExtensionContext context) {
    TransactionManager tm = AppConfig.singleton().getTransactionManager();
    tm.required(
        () -> {
          dao.create();
        });
  }

  @Override
  public void afterTestExecution(ExtensionContext context) {
    TransactionManager tm = AppConfig.singleton().getTransactionManager();
    tm.required(
        () -> {
          dao.drop();
        });
  }
}
