package tutorial;

import tutorial.dao.EmployeeDao;
import tutorial.dao.EmployeeDaoImpl;
import tutorial.entity.Employee;
import tutorial.entity.JobType;

public class UpdateTest extends TutorialTestCase {

    private final EmployeeDao dao = new EmployeeDaoImpl();

    public void testUpdate() throws Exception {
        Employee employee = dao.selectById(1);
        employee.setName("hoge");
        employee.setJobType(JobType.PRESIDENT);

        dao.update(employee);
    }

    public void testUpdateWithSqlFile() throws Exception {
        Employee employee = dao.selectById(1);
        employee.setName("hoge");
        employee.setJobType(JobType.PRESIDENT);

        dao.updateWithSqlFile(employee);
    }
}
