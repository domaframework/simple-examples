package example.jpms_java;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import example.jpms_java.entity.Employee;
import example.jpms_java.entity.JobType;
import example.jpms_java.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.doma.jdbc.Config;

@ExtendWith(TestEnvironment.class)
public class UpdateTest {

  private final EmployeeRepository repository;

  UpdateTest(Config config) {
    repository = new EmployeeRepository(config);
  }

  @Test
  public void testUpdate() {
    Employee employee = repository.selectById(1);
    assertNotNull(employee);
    employee.setName("hoge");
    employee.setJobType(JobType.PRESIDENT);
    repository.update(employee);
  }

  @Test
  public void testUpdateByNativeSql() {
    Employee employee = repository.selectById(1);
    assertNotNull(employee);
    employee.setName("hoge");
    employee.setJobType(JobType.PRESIDENT);
    repository.updateByNativeSql(employee);
  }
}
