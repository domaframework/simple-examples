package example.criteria_api;

import example.criteria_api.domain.Age;
import example.criteria_api.domain.Salary;
import example.criteria_api.entity.Employee;
import example.criteria_api.repository.EmployeeRepository;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TestEnvironment.class)
public class BatchInsertTest {

  private final EmployeeRepository repository;

  BatchInsertTest(DbConfig config) {
    repository = new EmployeeRepository(config);
  }

  @Test
  public void testBatchInsert() {
    var employee1 = new Employee();
    employee1.setName("test-1");
    employee1.setAge(new Age(30));
    employee1.setSalary(new Salary(300));

    var employee2 = new Employee();
    employee2.setName("test-2");
    employee2.setAge(new Age(40));
    employee2.setSalary(new Salary(500));

    repository.batchInsert(Arrays.asList(employee1, employee2));
  }
}
