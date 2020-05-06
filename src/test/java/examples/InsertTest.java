package examples;

import examples.dao.EmployeeDao;
import examples.dao.EmployeeDaoImpl;
import examples.domain.Age;
import examples.domain.Salary;
import examples.entity.Employee;
import examples.entity.JobType;
import java.sql.Timestamp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TestEnvironment.class)
public class InsertTest {

  private final DbConfig config;
  private final EmployeeDao dao;

  InsertTest(DbConfig config) {
    this.config = config;
    dao = new EmployeeDaoImpl(config);
  }

  @Test
  public void testInsert() {
    var tm = config.getTransactionManager();

    tm.required(
        () -> {
          var employee = new Employee();
          employee.setName("test");
          employee.setAge(new Age(50));
          employee.setSalary(new Salary(300));
          employee.setJobType(JobType.PRESIDENT);
          dao.insert(employee);
        });
  }

  @Test
  public void testInsertByNativeSql() {
    var tm = config.getTransactionManager();

    tm.required(
        () -> {
          var employee = new Employee();
          employee.setId(100);
          employee.setName("test");
          employee.setAge(new Age(50));
          employee.setSalary(new Salary(300));
          employee.setJobType(JobType.PRESIDENT);
          employee.setInsertTimestamp(new Timestamp(System.currentTimeMillis()));
          employee.setVersion(1);
          dao.insertByNativeSql(employee);
        });
  }
}
