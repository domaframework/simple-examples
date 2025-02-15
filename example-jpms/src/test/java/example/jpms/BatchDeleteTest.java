package example.jpms;

import example.jpms.entity.Employee;
import example.jpms.repository.EmployeeRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.doma.jdbc.Config;

@ExtendWith(TestEnvironment.class)
public class BatchDeleteTest {

  private final EmployeeRepository repository;

  BatchDeleteTest(Config config) {
    repository = new EmployeeRepository(config);
  }

  @Test
  public void testBatchDelete() {
    List<Employee> list = repository.selectAll();
    repository.batchDelete(list);
  }
}
