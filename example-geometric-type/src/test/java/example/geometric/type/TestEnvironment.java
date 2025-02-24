package example.geometric.type;

import example.geometric.type.dao.ScriptDao;
import example.geometric.type.dao.ScriptDaoImpl;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.seasar.doma.jdbc.Naming;
import org.seasar.doma.jdbc.SimpleConfig;
import org.seasar.doma.jdbc.tx.LocalTransactionManager;
import org.seasar.doma.slf4j.Slf4jJdbcLogger;

public class TestEnvironment
    implements BeforeAllCallback,
        AfterAllCallback,
        BeforeTestExecutionCallback,
        AfterTestExecutionCallback,
        ParameterResolver {

  private final LocalTransactionManager transactionManager;
  private final SimpleConfig config;
  private final ScriptDao dao;

  public TestEnvironment() {
    config =
        SimpleConfig.builder("jdbc:tc:postgresql:12.20:///test?TC_DAEMON=true", "test", "test")
            .jdbcLogger(new Slf4jJdbcLogger())
            .naming(Naming.SNAKE_LOWER_CASE)
            .build();
    transactionManager = config.getLocalTransactionManager();
    dao = new ScriptDaoImpl(config);
  }

  @Override
  public void beforeAll(ExtensionContext context) {
    transactionManager.required(dao::create);
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
    return parameterContext.getParameter().getType().isAssignableFrom(SimpleConfig.class);
  }

  public Object resolveParameter(
      ParameterContext parameterContext, ExtensionContext extensionContext) {
    return config;
  }
}
