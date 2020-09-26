package example.sql_annotation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import example.sql_annotation.dao.EmployeeDao;
import example.sql_annotation.dao.EmployeeDaoImpl;
import example.sql_annotation.domain.Age;
import example.sql_annotation.domain.Salary;
import example.sql_annotation.entity.Employee;
import example.sql_annotation.entity.JobType;
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
    var employee = new Employee();
    employee.setId(100);
    employee.setName("test");
    employee.setAge(new Age(50));
    employee.setSalary(new Salary(300));
    employee.setJobType(JobType.PRESIDENT);
    employee.setInsertTimestamp(new Timestamp(System.currentTimeMillis()));
    employee.setVersion(1);
    var rows = dao.insert(employee);
    assertEquals(1, rows);
  }
}
