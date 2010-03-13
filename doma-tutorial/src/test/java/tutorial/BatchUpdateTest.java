package tutorial;

import java.util.List;

import org.seasar.doma.jdbc.tx.LocalTransaction;

import tutorial.dao.EmployeeDao;
import tutorial.dao.EmployeeDaoImpl;
import tutorial.domain.Salary;
import tutorial.entity.Employee;

public class BatchUpdateTest extends TutorialTestCase {

    private final EmployeeDao dao = new EmployeeDaoImpl();

    public void testBatchUpdate() throws Exception {
        LocalTransaction tx = AppConfig.getLocalTransaction();
        try {
            tx.begin();

            List<Employee> list = dao.selectAll();
            for (Employee employee : list) {
                Salary salary = employee.getSalary();
                if (salary != null) {
                    employee.setSalary(salary.add(new Salary(100)));
                }
            }
            dao.batchUpdate(list);

            tx.commit();
        } finally {
            tx.rollback();
        }
    }
}
