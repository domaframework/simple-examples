package example.criteria;

import static org.junit.jupiter.api.Assertions.assertEquals;

import example.common.test.TestEnvironment;
import example.criteria.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.doma.jdbc.Config;

@ExtendWith(TestEnvironment.class)
public class AggregateFunctionTest {

  private final EmployeeRepository repository;

  AggregateFunctionTest(Config config) {
    repository = new EmployeeRepository(config);
  }

  @Test
  public void testCount() {
    assertEquals(14L, repository.selectCount());
  }
}
