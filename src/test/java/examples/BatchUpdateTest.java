package examples;

import examples.dao.EmployeeDao;
import examples.dao.EmployeeDaoImpl;
import examples.domain.Salary;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TestEnvironment.class)
public class BatchUpdateTest {

  private final DbConfig config;
  private final EmployeeDao dao;

  BatchUpdateTest(DbConfig config) {
    this.config = config;
    dao = new EmployeeDaoImpl(config);
  }

  @Test
  public void testBatchUpdate() {
    var tm = config.getTransactionManager();

    tm.required(
        () -> {
          var list = dao.selectAll();
          for (var employee : list) {
            var salary = employee.getSalary();
            if (salary != null) {
              employee.setSalary(salary.add(new Salary(100)));
            }
          }
          dao.batchUpdate(list);
        });
  }
}
