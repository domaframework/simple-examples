package example;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.seasar.doma.jdbc.IterationCallback;
import org.seasar.doma.jdbc.IterationContext;

import example.dao.EmployeeDao;
import example.dao.EmployeeDaoImpl;
import example.domain.Salary;
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

    public void testSelectByEnum() throws Exception {
        List<Employee> list = dao.selectByJobType(Employee.JobType.PRESIDENT);
        Logger.getAnonymousLogger().info(list.toString());
    }

    public void testSelectEnumList() throws Exception {
        List<Employee.JobType> list = dao.selectAllJobTypes();
        Logger.getAnonymousLogger().info(list.toString());
    }

    public void testSelectByDomain() throws Exception {
        List<Employee> list = dao.selectBySalary(new Salary(2900));
        Logger.getAnonymousLogger().info(list.toString());
    }

    public void testSelectDomain() throws Exception {
        Salary salary = dao.selectSummedSalary();
        Logger.getAnonymousLogger().info(salary.toString());
    }

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
