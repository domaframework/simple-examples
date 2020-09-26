package example.dao_style_basic.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import example.dao_style_basic.entity.Employee;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.doma.jdbc.Config;

@ExtendWith(TestEnvironment.class)
public class SelectTest {

  private final EmployeeDao dao;

  public SelectTest(Config config) {
    Objects.requireNonNull(config);
    this.dao = new EmployeeDaoImpl(config);
  }

  @Test
  public void testSelectAll() {
    List<Employee> employees = dao.selectAll();
    assertEquals(14, employees.size());
  }

  @Test
  public void testSelectById() {
    Employee employee = dao.selectById(1);
    assertNotNull(employee);
  }
}
