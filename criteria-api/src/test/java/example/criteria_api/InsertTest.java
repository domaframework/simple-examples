package example.criteria_api;

import example.criteria_api.domain.Age;
import example.criteria_api.domain.Salary;
import example.criteria_api.entity.Employee;
import example.criteria_api.entity.JobType;
import example.criteria_api.repository.EmployeeRepository;
import java.sql.Timestamp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TestEnvironment.class)
public class InsertTest {

  private final EmployeeRepository repository;

  InsertTest(DbConfig config) {
    repository = new EmployeeRepository(config);
  }

  @Test
  public void testInsert() {
    var employee = new Employee();
    employee.setName("test");
    employee.setAge(new Age(50));
    employee.setSalary(new Salary(300));
    employee.setJobType(JobType.PRESIDENT);
    repository.insert(employee);
  }

  @Test
  public void testInsertByNativeSql() {
    var employee = new Employee();
    employee.setId(100);
    employee.setName("test");
    employee.setAge(new Age(50));
    employee.setSalary(new Salary(300));
    employee.setJobType(JobType.PRESIDENT);
    employee.setInsertTimestamp(new Timestamp(System.currentTimeMillis()));
    employee.setVersion(1);
    repository.insertByNativeSql(employee);
  }
}
