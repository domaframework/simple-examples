package tutorial;

import org.seasar.doma.jdbc.tx.LocalTransaction;

import tutorial.dao.EmployeeDao;
import tutorial.dao.EmployeeDaoImpl;
import tutorial.entity.Employee;
import tutorial.entity.JobType;

public class UpdateTest extends TutorialTestCase {

    private final EmployeeDao dao = new EmployeeDaoImpl();

    public void testUpdate() throws Exception {
        LocalTransaction tx = AppConfig.getLocalTransaction();
        try {
            tx.begin();

            Employee employee = dao.selectById(1);
            employee.setName("hoge");
            employee.setJobType(JobType.PRESIDENT);
            dao.update(employee);

            tx.commit();
        } finally {
            tx.rollback();
        }
    }

    public void testUpdateWithSqlFile() throws Exception {
        LocalTransaction tx = AppConfig.getLocalTransaction();
        try {
            tx.begin();

            Employee employee = dao.selectById(1);
            employee.setName("hoge");
            employee.setJobType(JobType.PRESIDENT);
            dao.updateWithSqlFile(employee);

            tx.commit();
        } finally {
            tx.rollback();
        }
    }
}
