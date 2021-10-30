package example.jpms_java;

import org.seasar.doma.jdbc.dialect.H2Dialect;
import org.seasar.doma.jdbc.tx.LocalTransactionDataSource;
import org.seasar.doma.jdbc.tx.LocalTransactionManager;
import org.seasar.doma.slf4j.Slf4jJdbcLogger;

public class DbContextFactory {

  public static DbContext create() {
    var dialect = new H2Dialect();
    var dataSource =
        new LocalTransactionDataSource("jdbc:h2:mem:tutorial;DB_CLOSE_DELAY=-1", "sa", null);
    var jdbcLogger = new Slf4jJdbcLogger();
    var localTransaction = dataSource.getLocalTransaction(jdbcLogger);
    var transactionManager = new LocalTransactionManager(localTransaction);
    var config = new DbConfig(dialect, dataSource, jdbcLogger, transactionManager);
    return new DbContext(localTransaction, transactionManager, config);
  }
}
