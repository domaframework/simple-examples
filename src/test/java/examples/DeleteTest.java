package examples;

import static org.seasar.doma.internal.util.AssertionUtil.assertTrue;

import examples.dao.EmployeeDao;
import examples.dao.EmployeeDaoImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TestEnvironment.class)
public class DeleteTest {

  private final DbConfig config;
  private final EmployeeDao dao;

  DeleteTest(DbConfig config) {
    this.config = config;
    dao = new EmployeeDaoImpl(config);
  }

  @Test
  public void testDelete() {
    var tm = config.getTransactionManager();

    tm.required(
        () -> {
          var employee = dao.selectById(1);
          dao.delete(employee.orElseThrow());
        });
  }

  @Test
  public void testDeleteByNativeSql() {
    var tm = config.getTransactionManager();

    tm.required(
        () -> {
          var employee = dao.selectById(1);
          assertTrue(employee.isPresent());
          dao.deleteByNativeSql(employee.orElseThrow());
        });
  }
}
