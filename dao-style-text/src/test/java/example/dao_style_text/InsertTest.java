package example.dao_style_text;

import static org.junit.jupiter.api.Assertions.assertEquals;

import example.dao_style_text.dao.EmployeeDao;
import example.dao_style_text.dao.EmployeeDaoImpl;
import example.dao_style_text.domain.Age;
import example.dao_style_text.domain.Salary;
import example.dao_style_text.entity.Employee;
import example.dao_style_text.entity.JobType;
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
    var employee = new Employee();
    employee.setId(100);
    employee.setName("test");
    employee.setAge(new Age(50));
    employee.setSalary(new Salary(300));
    employee.setJobType(JobType.PRESIDENT);
    employee.setInsertTimestamp(LocalDateTime.now());
    employee.setVersion(1);
    var rows = dao.insert(employee);
    assertEquals(1, rows);
  }
}
