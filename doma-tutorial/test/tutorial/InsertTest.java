package tutorial;

import tutorial.dao.EmployeeDao;
import tutorial.dao.EmployeeDaoImpl;
import tutorial.domain.Salary;
import tutorial.entity.Employee;
import tutorial.entity.JobType;

public class InsertTest extends TutorialTestCase {

    private final EmployeeDao dao = new EmployeeDaoImpl();

    public void testInsert() throws Exception {
        Employee employee = new Employee();
        employee.setName("test");
        employee.setAge(50);
        employee.setSalary(new Salary(300));
        employee.setJobType(JobType.PRESIDENT);

        dao.insert(employee);
    }
}
