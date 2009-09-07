package example;

import java.util.List;

import example.dao.EmployeeDao;
import example.dao.EmployeeDao_;
import example.domain.Salary;
import example.entity.Employee;

public class BatchUpdateTest extends TutorialTestCase {

	private final EmployeeDao dao = new EmployeeDao_();

	public void testBatchUpdate() throws Exception {
		List<Employee> list = dao.selectAll();
		for (Employee employee : list) {
			Salary salary = employee.salary();
			if (!salary.isNull()) {
				employee.salary().set(salary.get() * 2);
			}
		}
		dao.batchUpdate(list);
	}
}
