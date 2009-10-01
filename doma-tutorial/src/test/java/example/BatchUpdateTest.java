package example;

import java.util.List;

import example.dao.EmployeeDao;
import example.dao.EmployeeDaoImpl;
import example.domain.Salary;
import example.entity.Employee;

public class BatchUpdateTest extends TutorialTestCase {

	private final EmployeeDao dao = new EmployeeDaoImpl();

	public void testBatchUpdate() throws Exception {
		List<Employee> list = dao.selectAll();
		for (Employee employee : list) {
			Salary salary = employee.getSalary();
			if (salary != null) {
				employee.setSalary(salary.add(new Salary(100)));
			}
		}
		dao.batchUpdate(list);
	}
}
