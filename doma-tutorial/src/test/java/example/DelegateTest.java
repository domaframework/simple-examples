package example;

import java.util.List;
import java.util.logging.Logger;

import example.dao.EmployeeDao;
import example.dao.EmployeeDao_;
import example.entity.Employee;

public class DelegateTest extends TutorialTestCase {

	private final EmployeeDao dao = new EmployeeDao_();

	public void testDaoDelegate() throws Exception {
		int count = dao.count();
		Logger.getAnonymousLogger().info(String.valueOf(count));
	}

	public void testEntityDelegate() throws Exception {
		List<Employee> list = dao.selectAll();
		for (Employee employee : list) {
			String description = employee.getSalaryDescription();
			Logger.getAnonymousLogger().info(description);
		}

	}
}
