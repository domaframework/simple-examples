package example;

import java.util.logging.Logger;

import example.dao.EmployeeDao;
import example.dao.EmployeeDaoImpl;
import example.domain.Salary;
import example.entity.Employee;

public class InsertTest extends TutorialTestCase {

	private final EmployeeDao dao = new EmployeeDaoImpl();

	public void testInsert() throws Exception {
		Employee employee = new Employee();
		employee.setId(99);
		employee.setName("test");
		employee.setSalary(new Salary(300));
		dao.insert(employee);
		Logger.getAnonymousLogger().info(employee.toString());
	}
}
