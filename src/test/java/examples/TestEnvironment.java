package examples;

import examples.dao.EmployeeDao;
import examples.dao.EmployeeDaoImpl;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;

public class TestEnvironment
    implements BeforeTestExecutionCallback, AfterTestExecutionCallback, ParameterResolver {

  private final DbConfig config = DbConfig.singleton();
  private final EmployeeDao dao = new EmployeeDaoImpl(config);

  @Override
  public void beforeTestExecution(ExtensionContext context) {
    var tm = DbConfig.singleton().getTransactionManager();
    tm.required(dao::create);
  }

  @Override
  public void afterTestExecution(ExtensionContext context) {
    var tm = DbConfig.singleton().getTransactionManager();
    tm.required(dao::drop);
  }

  public boolean supportsParameter(
      ParameterContext parameterContext, ExtensionContext extensionContext) {
    return parameterContext.getParameter().getType() == DbConfig.class;
  }

  public Object resolveParameter(
      ParameterContext parameterContext, ExtensionContext extensionContext) {
    return config;
  }
}
