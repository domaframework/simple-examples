package examples;

import static org.junit.jupiter.api.Assertions.assertEquals;

import examples.dao.EmployeeDao;
import examples.dao.EmployeeDaoImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TestEnvironment.class)
public class AggregateFunctionTest {

  private final DbConfig config;
  private final EmployeeDao dao;

  AggregateFunctionTest(DbConfig config) {
    this.config = config;
    dao = new EmployeeDaoImpl(config);
  }

  @Test
  public void testCount() {
    var tm = config.getTransactionManager();

    tm.required(() -> assertEquals(14L, dao.selectCount()));
  }
}
