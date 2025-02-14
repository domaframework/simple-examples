package example.dao_style_text;

import static org.junit.jupiter.api.Assertions.assertEquals;

import example.common.test.TestEnvironment;
import example.dao_style_text.dao.EmployeeDao;
import example.dao_style_text.dao.EmployeeDaoImpl;
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
    var employee = dao.selectById(1);
    var rows = dao.delete(employee);
    assertEquals(1, rows);
  }
}
