package example;

import java.util.Arrays;

import example.dao.EmployeeDao;
import example.dao.EmployeeDaoImpl;
import example.domain.Salary;
import example.entity.Employee;

public class BatchInsertTest extends TutorialTestCase {

	private final EmployeeDao dao = new EmployeeDaoImpl();

	public void testBatchInsert() throws Exception {
		Employee employee1 = new Employee();
		employee1.setId(99);
		employee1.setName("test-1");
		employee1.setSalary(new Salary(300));

		Employee employee2 = new Employee();
		employee2.setId(100);
		employee2.setName("test-2");
		employee2.setSalary(new Salary(500));

		dao.batchInsert(Arrays.asList(employee1, employee2));
	}
}
