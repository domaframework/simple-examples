package example.criteria_api;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import example.criteria_api.entity.JobType;
import example.criteria_api.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TestEnvironment.class)
public class UpdateTest {

  private final EmployeeRepository repository;

  UpdateTest(DbConfig config) {
    repository = new EmployeeRepository(config);
  }

  @Test
  public void testUpdate() {
    var employee = repository.selectById(1);
    assertNotNull(employee);
    employee.setName("hoge");
    employee.setJobType(JobType.PRESIDENT);
    repository.update(employee);
  }

  @Test
  public void testUpdateByNativeSql() {
    var employee = repository.selectById(1);
    assertNotNull(employee);
    employee.setName("hoge");
    employee.setJobType(JobType.PRESIDENT);
    repository.updateByNativeSql(employee);
  }
}
