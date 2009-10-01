package example;

import java.util.logging.Logger;

import org.seasar.doma.jdbc.IterationCallback;
import org.seasar.doma.jdbc.IterationContext;

import example.dao.EmployeeDao;
import example.dao.EmployeeDaoImpl;
import example.domain.Salary;
import example.entity.Employee;

public class IterateTest extends TutorialTestCase {

	private final EmployeeDao dao = new EmployeeDaoImpl();

	public void testIterate() throws Exception {
		Salary sum = dao.selectAll(new IterationCallback<Salary, Employee>() {

			private Salary sum = new Salary(0);

			@Override
			public Salary iterate(Employee target, IterationContext context) {
				Salary salary = target.getSalary();
				if (salary != null) {
					sum = sum.add(salary);
				}
				return sum;
			}
		});
		Logger.getAnonymousLogger().info(sum.toString());
	}

	public void testIterate_exit() throws Exception {
		Salary sum = dao.selectAll(new IterationCallback<Salary, Employee>() {

			private Salary sum = new Salary(0);

			@Override
			public Salary iterate(Employee target, IterationContext context) {
				Salary salary = target.getSalary();
				if (salary != null) {
					sum = sum.add(salary);
				}
				if (sum.getValue() != null && sum.getValue() > 10000) {
					context.exit();
				}
				return sum;
			}
		});
		Logger.getAnonymousLogger().info(sum.toString());
	}
}
