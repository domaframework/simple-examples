package example.dao_style_basic.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import example.common.entity.Employee;
import example.common.test.TestEnvironment;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.doma.jdbc.Config;

@ExtendWith(TestEnvironment.class)
public class DeleteTest {

  private final EmployeeDao dao;

  public DeleteTest(Config config) {
    Objects.requireNonNull(config);
    this.dao = new EmployeeDaoImpl(config);
  }

  @Test
  public void testDelete() {
    Employee employee = dao.selectById(1);
    int rows = dao.delete(employee);
    assertEquals(1, rows);
  }
}
