package example;

import java.util.Arrays;

import example.dao.EmployeeDao;
import example.dao.EmployeeDao_;
import example.entity.Employee;
import example.entity.Employee_;

public class BatchInsertTest extends TutorialTestCase {

	private final EmployeeDao dao = new EmployeeDao_();

	public void testBatchInsert() throws Exception {
		Employee employee1 = new Employee_();
		employee1.id().set(99);
		employee1.name().set("test-1");
		employee1.salary().set(300);

		Employee employee2 = new Employee_();
		employee2.id().set(100);
		employee2.name().set("test-2");
		employee2.salary().set(500);

		dao.batchInsert(Arrays.asList(employee1, employee2));
	}
}
