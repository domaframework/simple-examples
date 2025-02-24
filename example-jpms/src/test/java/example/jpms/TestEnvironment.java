package example.jpms;

import example.jpms.dao.ScriptDao;
import example.jpms.dao.ScriptDaoImpl;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.seasar.doma.jdbc.SimpleConfig;
import org.seasar.doma.jdbc.tx.LocalTransactionManager;

public class TestEnvironment
    implements BeforeAllCallback,
        AfterAllCallback,
        BeforeTestExecutionCallback,
        AfterTestExecutionCallback,
        ParameterResolver {

  private final SimpleConfig config;
  private final LocalTransactionManager transactionManager;
  private final ScriptDao dao;

  public TestEnvironment() {
    config = SimpleConfigFactory.create();
    transactionManager = config.getLocalTransactionManager();
    dao = new ScriptDaoImpl(config);
  }

  @Override
  public void beforeAll(ExtensionContext context) {
    config.getTransactionManager().required(dao::create);
  }

  @Override
  public void afterAll(ExtensionContext context) {
    transactionManager.required(dao::drop);
  }

  @Override
  public void beforeTestExecution(ExtensionContext context) {
    transactionManager.getTransaction().begin();
  }

  @Override
  public void afterTestExecution(ExtensionContext context) {
    transactionManager.getTransaction().rollback();
  }

  public boolean supportsParameter(
      ParameterContext parameterContext, ExtensionContext extensionContext) {
    return parameterContext.getParameter().getType().isAssignableFrom(DbConfig.class);
  }

  public Object resolveParameter(
      ParameterContext parameterContext, ExtensionContext extensionContext) {
    return config;
  }
}
