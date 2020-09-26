package example.dsl_style;

import example.dsl_style.repository.EmployeeRepository;
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
    var list = repository.selectAll();
    repository.batchDelete(list);
  }
}
