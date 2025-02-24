package example.jpms;

import org.seasar.doma.jdbc.SimpleConfig;
import org.seasar.doma.slf4j.Slf4jJdbcLogger;

public class SimpleConfigFactory {

  public static SimpleConfig create() {
    return SimpleConfig.builder("jdbc:h2:mem:tutorial;DB_CLOSE_DELAY=-1", "sa", null)
        .jdbcLogger(new Slf4jJdbcLogger())
        .build();
  }
}
