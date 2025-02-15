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

public class TestEnvironment
    implements BeforeAllCallback,
        AfterAllCallback,
        BeforeTestExecutionCallback,
        AfterTestExecutionCallback,
        ParameterResolver {

  private final DbContext dbContext;
  private final ScriptDao dao;

  public TestEnvironment() {
    dbContext = DbContextFactory.create();
    dao = new ScriptDaoImpl(dbContext.config);
  }

  @Override
  public void beforeAll(ExtensionContext context) {
    dbContext.transactionManager.required(dao::create);
  }

  @Override
  public void afterAll(ExtensionContext context) {
    dbContext.transactionManager.required(dao::drop);
  }

  @Override
  public void beforeTestExecution(ExtensionContext context) {
    dbContext.localTransaction.begin();
  }

  @Override
  public void afterTestExecution(ExtensionContext context) {
    dbContext.localTransaction.rollback();
  }

  public boolean supportsParameter(
      ParameterContext parameterContext, ExtensionContext extensionContext) {
    return parameterContext.getParameter().getType().isAssignableFrom(DbConfig.class);
  }

  public Object resolveParameter(
      ParameterContext parameterContext, ExtensionContext extensionContext) {
    return dbContext.config;
  }
}
