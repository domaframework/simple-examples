package example.dao_style_file;

import static org.junit.jupiter.api.Assertions.assertEquals;

import example.common.domain.Age;
import example.common.domain.Salary;
import example.common.entity.Employee;
import example.common.entity.JobType;
import example.common.test.TestEnvironment;
import example.dao_style_file.dao.EmployeeDao;
import example.dao_style_file.dao.EmployeeDaoImpl;
import java.time.LocalDateTime;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.doma.jdbc.Config;

@ExtendWith(TestEnvironment.class)
public class InsertTest {

  private final EmployeeDao dao;

  public InsertTest(Config config) {
    Objects.requireNonNull(config);
    this.dao = new EmployeeDaoImpl(config);
  }

  @Test
  public void testInsert() {
    Employee employee = new Employee();
    employee.setId(100);
    employee.setName("test");
    employee.setAge(new Age(50));
    employee.setSalary(new Salary(300));
    employee.setJobType(JobType.PRESIDENT);
    employee.setInsertTimestamp(LocalDateTime.now());
    employee.setVersion(1);
    int rows = dao.insert(employee);
    assertEquals(1, rows);
  }
}
