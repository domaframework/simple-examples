package example.dsl_style_java;

import example.dsl_style_java.dao.ScriptDao;
import example.dsl_style_java.dao.ScriptDaoImpl;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.seasar.doma.jdbc.JdbcLogger;
import org.seasar.doma.jdbc.dialect.H2Dialect;
import org.seasar.doma.jdbc.tx.LocalTransaction;
import org.seasar.doma.jdbc.tx.LocalTransactionDataSource;
import org.seasar.doma.jdbc.tx.LocalTransactionManager;
import org.seasar.doma.slf4j.Slf4jJdbcLogger;

public class TestEnvironment
    implements BeforeAllCallback,
        AfterAllCallback,
        BeforeTestExecutionCallback,
        AfterTestExecutionCallback,
        ParameterResolver {

  private final LocalTransaction localTransaction;
  private final LocalTransactionManager transactionManager;
  private final DbConfig config;
  private final ScriptDao dao;

  public TestEnvironment() {
    H2Dialect dialect = new H2Dialect();
    LocalTransactionDataSource dataSource =
        new LocalTransactionDataSource("jdbc:h2:mem:tutorial;DB_CLOSE_DELAY=-1", "sa", null);
    JdbcLogger jdbcLogger = new Slf4jJdbcLogger();
    localTransaction = dataSource.getLocalTransaction(jdbcLogger);
    transactionManager = new LocalTransactionManager(localTransaction);
    config = new DbConfig(dialect, dataSource, jdbcLogger, transactionManager);
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
    localTransaction.begin();
  }

  @Override
  public void afterTestExecution(ExtensionContext context) {
    localTransaction.rollback();
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
