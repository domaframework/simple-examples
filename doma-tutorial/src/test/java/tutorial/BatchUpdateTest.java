package tutorial;

import java.util.List;

import tutorial.dao.EmployeeDao;
import tutorial.dao.EmployeeDaoImpl;
import tutorial.domain.Salary;
import tutorial.entity.Employee;


public class BatchUpdateTest extends TutorialTestCase {

    private final EmployeeDao dao = new EmployeeDaoImpl();

    public void testBatchUpdate() throws Exception {
        List<Employee> list = dao.selectAll();
        for (Employee employee : list) {
            Salary salary = employee.getSalary();
            if (salary != null) {
                employee.setSalary(salary.add(new Salary(100)));
            }
        }
        dao.batchUpdate(list);
    }
}
