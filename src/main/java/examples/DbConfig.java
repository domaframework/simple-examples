package examples;

import javax.sql.DataSource;
import org.seasar.doma.SingletonConfig;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.JdbcLogger;
import org.seasar.doma.jdbc.Slf4jJdbcLogger;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.H2Dialect;
import org.seasar.doma.jdbc.tx.LocalTransactionDataSource;
import org.seasar.doma.jdbc.tx.LocalTransactionManager;
import org.seasar.doma.jdbc.tx.TransactionManager;

@SingletonConfig
public class DbConfig implements Config {

  private static final DbConfig CONFIG = new DbConfig();

  private final Dialect dialect;

  private final LocalTransactionDataSource dataSource;

  private final JdbcLogger jdbcLogger;

  private final TransactionManager transactionManager;

  private DbConfig() {
    jdbcLogger = new Slf4jJdbcLogger();
    dialect = new H2Dialect();
    dataSource =
        new LocalTransactionDataSource("jdbc:h2:mem:tutorial;DB_CLOSE_DELAY=-1", "sa", null);
    transactionManager = new LocalTransactionManager(dataSource.getLocalTransaction(jdbcLogger));
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

  public static DbConfig singleton() {
    return CONFIG;
  }
}
