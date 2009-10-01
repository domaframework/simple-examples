package example;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import example.dao.EmployeeDao;
import example.dao.EmployeeDaoImpl;
import example.entity.Employee;

public class SelectTest extends TutorialTestCase {

	private final EmployeeDao dao = new EmployeeDaoImpl();

	public void testIn() throws Exception {
		List<String> names = Arrays.asList("JONES", "SCOTT");
		List<Employee> list = dao.selectByNames(names);
		for (Employee employee : list) {
			Logger.getAnonymousLogger().info(employee.toString());
		}
	}
}
