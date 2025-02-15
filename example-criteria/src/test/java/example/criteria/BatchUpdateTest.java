package example.criteria;

import example.common.domain.Salary;
import example.common.entity.Employee;
import example.common.test.TestEnvironment;
import example.criteria.repository.EmployeeRepository;
import java.util.List;
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
    List<Employee> list = repository.selectAll();
    for (Employee employee : list) {
      Salary salary = employee.getSalary();
      if (salary != null) {
        employee.setSalary(salary.add(new Salary(100)));
      }
    }
    repository.batchUpdate(list);
  }
}
