package example.criteria;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import example.common.entity.Employee;
import example.common.test.TestEnvironment;
import example.criteria.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.doma.jdbc.Config;

@ExtendWith(TestEnvironment.class)
public class DeleteTest {

  private final EmployeeRepository repository;

  DeleteTest(Config config) {
    repository = new EmployeeRepository(config);
  }

  @Test
  public void testDelete() {
    Employee employee = repository.selectById(1);
    assertNotNull(employee);
    repository.delete(employee);
  }

  @Test
  public void testDeleteByWhereExpression() {
    Employee employee = repository.selectById(1);
    assertNotNull(employee);
    repository.deleteByWhereExpression(employee);
  }
}
