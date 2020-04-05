package examples;

import examples.dao.EmployeeDao;
import examples.dao.EmployeeDaoImpl;
import examples.domain.Salary;
import examples.entity.Employee;
import examples.entity.JobType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.doma.jdbc.tx.TransactionManager;

import java.sql.Timestamp;

@ExtendWith(TestEnvironment.class)
public class InsertTest {

  private final EmployeeDao dao = new EmployeeDaoImpl();

  @Test
  public void testInsert() throws Exception {
    TransactionManager tm = AppConfig.singleton().getTransactionManager();

    tm.required(
        () -> {
          Employee employee = new Employee();
          employee.setName("test");
          employee.setAge(50);
          employee.setSalary(new Salary(300));
          employee.setJobType(JobType.PRESIDENT);
          dao.insert(employee);
        });
  }

  @Test
  public void testInsertWithSqlFile() throws Exception {
    TransactionManager tm = AppConfig.singleton().getTransactionManager();

    tm.required(
        () -> {
          Employee employee = new Employee();
          employee.setId(100);
          employee.setName("test");
          employee.setAge(50);
          employee.setSalary(new Salary(300));
          employee.setJobType(JobType.PRESIDENT);
          employee.setInsertTimestamp(new Timestamp(System.currentTimeMillis()));
          employee.setVersion(1);
          dao.insertWithSqlFile(employee);
        });
  }
}
