package example.criteria_api;

import example.criteria_api.domain.Salary;
import example.criteria_api.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TestEnvironment.class)
public class BatchUpdateTest {

  private final EmployeeRepository repository;

  BatchUpdateTest(DbConfig config) {
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