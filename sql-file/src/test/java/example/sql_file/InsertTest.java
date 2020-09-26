package example.sql_file;

import static org.junit.jupiter.api.Assertions.assertEquals;

import example.sql_file.dao.EmployeeDao;
import example.sql_file.dao.EmployeeDaoImpl;
import example.sql_file.domain.Age;
import example.sql_file.domain.Salary;
import example.sql_file.entity.Employee;
import example.sql_file.entity.JobType;
import java.sql.Timestamp;
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
    employee.setInsertTimestamp(new Timestamp(System.currentTimeMillis()));
    employee.setVersion(1);
    int rows = dao.insert(employee);
    assertEquals(1, rows);
  }
}
