package example.criteria;

import example.common.domain.Age;
import example.common.domain.Salary;
import example.common.entity.Employee;
import example.common.entity.JobType;
import example.common.test.TestEnvironment;
import example.criteria.repository.EmployeeRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.doma.jdbc.Config;

@ExtendWith(TestEnvironment.class)
public class InsertTest {

  private final EmployeeRepository repository;

  InsertTest(Config config) {
    repository = new EmployeeRepository(config);
  }

  @Test
  public void testInsert() {
    Employee employee = new Employee();
    employee.setName("test");
    employee.setAge(new Age(50));
    employee.setSalary(new Salary(300));
    employee.setJobType(JobType.PRESIDENT);
    repository.insert(employee);
  }

  @Test
  public void testInsertWithSpecifiedValues() {
    Employee employee = new Employee();
    employee.setId(100);
    employee.setName("test");
    employee.setAge(new Age(50));
    employee.setSalary(new Salary(300));
    employee.setJobType(JobType.PRESIDENT);
    employee.setInsertTimestamp(LocalDateTime.now());
    employee.setVersion(1);
    repository.insertWithSpecifiedValues(employee);
  }
}
