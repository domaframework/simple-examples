package example;

import example.dao.EmployeeDao;
import example.dao.EmployeeDaoImpl;
import example.entity.Employee;

public class UpdateTest extends TutorialTestCase {

	private final EmployeeDao dao = new EmployeeDaoImpl();

	public void testUpdate() throws Exception {
		Employee employee = dao.selectById(1);
		employee.setName("hoge");
		dao.update(employee);
	}
}
