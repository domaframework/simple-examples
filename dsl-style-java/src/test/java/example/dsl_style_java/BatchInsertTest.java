package example.dsl_style_java;

import example.common.domain.Age;
import example.common.domain.Salary;
import example.common.entity.Employee;
import example.common.test.TestEnvironment;
import example.dsl_style_java.repository.EmployeeRepository;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.doma.jdbc.Config;

@ExtendWith(TestEnvironment.class)
public class BatchInsertTest {

  private final EmployeeRepository repository;

  BatchInsertTest(Config config) {
    repository = new EmployeeRepository(config);
  }

  @Test
  public void testBatchInsert() {
    Employee employee1 = new Employee();
    employee1.setName("test-1");
    employee1.setAge(new Age(30));
    employee1.setSalary(new Salary(300));

    Employee employee2 = new Employee();
    employee2.setName("test-2");
    employee2.setAge(new Age(40));
    employee2.setSalary(new Salary(500));

    repository.batchInsert(Arrays.asList(employee1, employee2));
  }
}
