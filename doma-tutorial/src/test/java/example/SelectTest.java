package example;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import example.dao.EmployeeDao;
import example.dao.EmployeeDao_;
import example.domain.Name;
import example.entity.Employee;

public class SelectTest extends TutorialTestCase {

	private final EmployeeDao dao = new EmployeeDao_();

	public void testIn() throws Exception {
		List<Name> names = Arrays.asList(new Name("JONES"), new Name("SCOTT"));
		List<Employee> list = dao.selectByNames(names);
		for (Employee employee : list) {
			Logger.getAnonymousLogger().info(employee.toString());
		}
	}
}
