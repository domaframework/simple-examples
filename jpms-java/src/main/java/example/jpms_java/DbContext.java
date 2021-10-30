package example.jpms_java;

import java.util.Objects;
import org.seasar.doma.jdbc.tx.LocalTransaction;
import org.seasar.doma.jdbc.tx.LocalTransactionManager;

public class DbContext {
  public final LocalTransaction localTransaction;
  public final LocalTransactionManager transactionManager;
  public final DbConfig config;

  public DbContext(
      LocalTransaction localTransaction,
      LocalTransactionManager transactionManager,
      DbConfig config) {
    this.localTransaction = Objects.requireNonNull(localTransaction);
    this.transactionManager = Objects.requireNonNull(transactionManager);
    this.config = Objects.requireNonNull(config);
  }
}
