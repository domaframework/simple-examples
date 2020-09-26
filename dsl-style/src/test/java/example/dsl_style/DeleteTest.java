package example.dsl_style;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import example.dsl_style.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TestEnvironment.class)
public class DeleteTest {

  private final EmployeeRepository dao;

  DeleteTest(DbConfig config) {
    dao = new EmployeeRepository(config);
  }

  @Test
  public void testDelete() {
    var employee = dao.selectById(1);
    assertNotNull(employee);
    dao.delete(employee);
  }

  @Test
  public void testDeleteByNativeSql() {
    var employee = dao.selectById(1);
    assertNotNull(employee);
    dao.deleteByNativeSql(employee);
  }
}
