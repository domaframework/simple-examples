package example;

import java.util.logging.Logger;

import example.dao.EmployeeDao;
import example.dao.EmployeeDao_;
import example.entity.Employee;
import example.entity.Employee_;

public class InsertTest extends TutorialTestCase {

	private final EmployeeDao dao = new EmployeeDao_();

	public void testInsert() throws Exception {
		Employee employee = new Employee_();
		employee.id().set(99);
		employee.name().set("test");
		employee.salary().set(300);
		dao.insert(employee);
		Logger.getAnonymousLogger().info(employee.toString());
	}
}
