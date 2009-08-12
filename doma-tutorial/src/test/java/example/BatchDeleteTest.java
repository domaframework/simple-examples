package example;

import java.util.List;

import example.dao.EmployeeDao;
import example.dao.EmployeeDao_;
import example.entity.Employee;

public class BatchDeleteTest extends TutorialTestCase {

	private final EmployeeDao dao = new EmployeeDao_();

	public void testBatchDelete() throws Exception {
		List<Employee> list = dao.selectAll();
		dao.batchDelete(list);
	}
}
