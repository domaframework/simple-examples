package example;

import java.util.logging.Logger;

import org.seasar.doma.jdbc.IterationCallback;
import org.seasar.doma.jdbc.IterationContext;

import example.dao.EmployeeDao;
import example.dao.EmployeeDao_;
import example.domain.Salary;
import example.entity.Employee;

public class IterateTest extends TutorialTestCase {

	private final EmployeeDao dao = new EmployeeDao_();

	public void testIterate() throws Exception {
		Integer sum = dao.selectAll(new IterationCallback<Integer, Employee>() {

			private int sum = 0;

			@Override
			public Integer iterate(Employee target, IterationContext context) {
				Salary salary = target.salary();
				if (salary.isNotNull()) {
					sum += salary.get();
				}
				return sum;
			}
		});
		Logger.getAnonymousLogger().info(String.valueOf(sum));
	}

	public void testIterate_exit() throws Exception {
		Integer sum = dao.selectAll(new IterationCallback<Integer, Employee>() {

			private int sum = 0;

			@Override
			public Integer iterate(Employee target, IterationContext context) {
				Salary salary = target.salary();
				if (salary.isNotNull()) {
					sum += salary.get();
				}
				if (sum > 10000) {
					context.exit();
				}
				return sum;
			}
		});
		Logger.getAnonymousLogger().info(String.valueOf(sum));
	}
}
