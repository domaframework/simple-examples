package example;

import example.dao.EmployeeDao;
import example.dao.EmployeeDaoImpl;
import example.entity.Employee;

public class DeleteTest extends TutorialTestCase {

    private final EmployeeDao dao = new EmployeeDaoImpl();

    public void testDelete() throws Exception {
        Employee employee = dao.selectById(1);
        dao.delete(employee);
    }
}
