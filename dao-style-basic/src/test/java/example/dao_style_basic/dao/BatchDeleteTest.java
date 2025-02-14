package example.dao_style_basic.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import example.common.entity.Employee;
import example.common.test.TestEnvironment;
import java.util.Arrays;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.doma.jdbc.Config;

@ExtendWith(TestEnvironment.class)
public class BatchDeleteTest {

  private final EmployeeDao dao;

  public BatchDeleteTest(Config config) {
    Objects.requireNonNull(config);
    this.dao = new EmployeeDaoImpl(config);
  }

  @Test
  public void testBatchDelete() {
    Employee employee1 = dao.selectById(1);
    Employee employee2 = dao.selectById(2);
    int[] rows = dao.batchDelete(Arrays.asList(employee1, employee2));
    assertEquals(2, rows.length);

    assertNull(dao.selectById(1));
    assertNull(dao.selectById(2));
  }
}
