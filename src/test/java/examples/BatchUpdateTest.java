package examples;

import examples.dao.EmployeeDao;
import examples.dao.EmployeeDaoImpl;
import examples.domain.Salary;
import examples.entity.Employee;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.doma.jdbc.tx.TransactionManager;

@ExtendWith(TestEnvironment.class)
public class BatchUpdateTest {

  private final EmployeeDao dao = new EmployeeDaoImpl();

  @Test
  public void testBatchUpdate() {
    TransactionManager tm = AppConfig.singleton().getTransactionManager();

    tm.required(
        () -> {
          List<Employee> list = dao.selectAll();
          for (Employee employee : list) {
            Salary salary = employee.getSalary();
            if (salary != null) {
              employee.setSalary(salary.add(new Salary(100)));
            }
          }
          dao.batchUpdate(list);
        });
  }
}
