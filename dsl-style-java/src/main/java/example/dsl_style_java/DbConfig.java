package example.dsl_style_java;

import java.util.Objects;
import javax.sql.DataSource;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.JdbcLogger;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.tx.TransactionManager;

public class DbConfig implements Config {

  private final Dialect dialect;
  private final DataSource dataSource;
  private final JdbcLogger jdbcLogger;
  private final TransactionManager transactionManager;

  public DbConfig(
      Dialect dialect,
      DataSource dataSource,
      JdbcLogger jdbcLogger,
      TransactionManager transactionManager) {
    this.dialect = Objects.requireNonNull(dialect);
    this.dataSource = Objects.requireNonNull(dataSource);
    this.jdbcLogger = Objects.requireNonNull(jdbcLogger);
    this.transactionManager = Objects.requireNonNull(transactionManager);
  }

  @Override
  public JdbcLogger getJdbcLogger() {
    return jdbcLogger;
  }

  @Override
  public Dialect getDialect() {
    return dialect;
  }

  @Override
  public DataSource getDataSource() {
    return dataSource;
  }

  @Override
  public TransactionManager getTransactionManager() {
    return transactionManager;
  }
}
