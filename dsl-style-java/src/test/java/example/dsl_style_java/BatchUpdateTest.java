package example.dsl_style_java;

import example.dsl_style_java.domain.Salary;
import example.dsl_style_java.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.doma.jdbc.Config;

@ExtendWith(TestEnvironment.class)
public class BatchUpdateTest {

  private final EmployeeRepository repository;

  BatchUpdateTest(Config config) {
    repository = new EmployeeRepository(config);
  }

  @Test
  public void testBatchUpdate() {
    var list = repository.selectAll();
    for (var employee : list) {
      var salary = employee.getSalary();
      if (salary != null) {
        employee.setSalary(salary.add(new Salary(100)));
      }
    }
    repository.batchUpdate(list);
  }
}
