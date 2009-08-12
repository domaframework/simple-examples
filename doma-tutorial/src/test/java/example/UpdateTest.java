package example;

import example.dao.EmployeeDao;
import example.dao.EmployeeDao_;
import example.domain.Identity;
import example.entity.Employee;

public class UpdateTest extends TutorialTestCase {

	private final EmployeeDao dao = new EmployeeDao_();

	public void testDelete() throws Exception {
		Employee employee = dao.selectById(new Identity(1));
		employee.name().set("hoge");
		dao.update(employee);
	}
}
