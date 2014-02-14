package tutorial;

import org.junit.Rule;
import org.junit.Test;
import org.seasar.doma.jdbc.tx.KeepAliveLocalTransaction;

import tutorial.dao.EmployeeDao;
import tutorial.dao.EmployeeDaoImpl;
import tutorial.domain.Salary;
import tutorial.entity.Employee;
import tutorial.entity.JobType;

public class KeepAliveLocalTransactionTest {

    @Rule
    public final DbResource dbResource = new DbResource();

    private final EmployeeDao dao = new EmployeeDaoImpl();

    @Test
    public void testInsert() throws Exception {
        KeepAliveLocalTransaction tx = AppConfig.singleton()
                .getKeepAliveLocalTransaction();

        tx.init();
        try {
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

            try {
                tx.begin();
                Employee employee = new Employee();
                employee.setName("test2");
                employee.setAge(50);
                employee.setSalary(new Salary(300));
                employee.setJobType(JobType.PRESIDENT);
                dao.insert(employee);
                tx.commit();
            } finally {
                tx.rollback();
            }
        } finally {
            tx.destroy();
        }
    }
}
