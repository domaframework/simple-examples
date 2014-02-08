package tutorial;

import java.sql.Timestamp;

import org.seasar.doma.jdbc.tx.LocalTransaction;

import tutorial.dao.EmployeeDao;
import tutorial.dao.EmployeeDaoImpl;
import tutorial.domain.Salary;
import tutorial.entity.Employee;
import tutorial.entity.JobType;

public class InsertTest extends TutorialTestCase {

    private final EmployeeDao dao = new EmployeeDaoImpl();

    public void testInsert() throws Exception {
        LocalTransaction tx = AppConfig.getLocalTransaction();
        try {
            tx.begin();

            Employee employee = new Employee();
            employee.setName("test");
            employee.setAge(50);
            employee.setSalary(new Salary(300));
            employee.setJobType(JobType.PRESIDENT);
            dao.insert(employee);

            tx.commit();
        } finally {
            tx.rollback();
        }
    }

    public void testInsertWithSqlFile() throws Exception {
        LocalTransaction tx = AppConfig.getLocalTransaction();
        try {
            tx.begin();

            Employee employee = new Employee();
            employee.setId(100);
            employee.setName("test");
            employee.setAge(50);
            employee.setSalary(new Salary(300));
            employee.setJobType(JobType.PRESIDENT);
            employee.setInsertTimestamp(new Timestamp(System
                    .currentTimeMillis()));
            employee.setVersion(1);
            dao.insertWithSqlFile(employee);

            tx.commit();
        } finally {
            tx.rollback();
        }
    }
}
