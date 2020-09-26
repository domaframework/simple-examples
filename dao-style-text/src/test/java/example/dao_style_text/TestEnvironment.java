package example.dao_style_text;

import example.dao_style_text.dao.ScriptDao;
import example.dao_style_text.dao.ScriptDaoImpl;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.seasar.doma.jdbc.Slf4jJdbcLogger;
import org.seasar.doma.jdbc.dialect.H2Dialect;
import org.seasar.doma.jdbc.tx.LocalTransaction;
import org.seasar.doma.jdbc.tx.LocalTransactionDataSource;
import org.seasar.doma.jdbc.tx.LocalTransactionManager;
import org.seasar.doma.jdbc.tx.TransactionManager;

public class TestEnvironment
    implements BeforeAllCallback,
        AfterAllCallback,
        BeforeTestExecutionCallback,
        AfterTestExecutionCallback,
        ParameterResolver {

  private final LocalTransaction localTransaction;
  private final TransactionManager transactionManager;
  private final DbConfig config;
  private final ScriptDao dao;

  public TestEnvironment() {
    var dialect = new H2Dialect();
    var dataSource =
        new LocalTransactionDataSource("jdbc:h2:mem:tutorial;DB_CLOSE_DELAY=-1", "sa", null);
    var jdbcLogger = new Slf4jJdbcLogger();
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
