package examples;

import examples.dao.EmployeeDao;
import examples.dao.EmployeeDaoImpl;
import examples.domain.Age;
import examples.domain.Salary;
import examples.entity.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.doma.jdbc.tx.TransactionManager;

import java.util.Arrays;

@ExtendWith(TestEnvironment.class)
public class BatchInsertTest {

  private final EmployeeDao dao = new EmployeeDaoImpl();

  @Test
  public void testBatchInsert() {
    TransactionManager tm = AppConfig.singleton().getTransactionManager();

    tm.required(
        () -> {
          Employee employee1 = new Employee();
          employee1.setName("test-1");
          employee1.setAge(new Age(30));
          employee1.setSalary(new Salary(300));

          Employee employee2 = new Employee();
          employee2.setName("test-2");
          employee2.setAge(new Age(40));
          employee2.setSalary(new Salary(500));

          dao.batchInsert(Arrays.asList(employee1, employee2));
        });
  }
}
