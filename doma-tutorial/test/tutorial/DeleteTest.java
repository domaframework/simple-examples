package tutorial;

import tutorial.dao.EmployeeDao;
import tutorial.dao.EmployeeDaoImpl;
import tutorial.entity.Employee;

public class DeleteTest extends TutorialTestCase {

    private final EmployeeDao dao = new EmployeeDaoImpl();

    public void testDelete() throws Exception {
        Employee employee = dao.selectById(1);

        dao.delete(employee);
    }
}
