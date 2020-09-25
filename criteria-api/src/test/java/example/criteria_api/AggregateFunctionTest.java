package example.criteria_api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import example.criteria_api.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TestEnvironment.class)
public class AggregateFunctionTest {

  private final EmployeeRepository repository;

  AggregateFunctionTest(DbConfig config) {
    repository = new EmployeeRepository(config);
  }

  @Test
  public void testCount() {
    assertEquals(14L, repository.selectCount());
  }
}
