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
import org.seasar.doma.jdbc.JdbcLogger;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.PostgresDialect;
import org.seasar.doma.jdbc.tx.LocalTransactionDataSource;
import org.seasar.doma.jdbc.tx.LocalTransactionManager;
import org.seasar.doma.slf4j.Slf4jJdbcLogger;

public class TestEnvironment
    implements BeforeAllCallback,
        AfterAllCallback,
        BeforeTestExecutionCallback,
        AfterTestExecutionCallback,
        ParameterResolver {

  private final LocalTransactionManager transactionManager;
  private final DbConfig config;
  private final ScriptDao dao;

  public TestEnvironment() {
    Dialect dialect = new PostgresDialect();
    LocalTransactionDataSource dataSource =
        new LocalTransactionDataSource(
            "jdbc:tc:postgresql:12.20:///test?TC_DAEMON=true", "test", "test");
    JdbcLogger jdbcLogger = new Slf4jJdbcLogger();
    transactionManager = new LocalTransactionManager(dataSource, jdbcLogger);
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
