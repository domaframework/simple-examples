package examples;

import java.sql.Timestamp;

import org.junit.Rule;
import org.junit.Test;
import org.seasar.doma.jdbc.tx.LocalTransactionManager;

import examples.AppConfig;
import examples.dao.EmployeeDao;
import examples.dao.EmployeeDaoImpl;
import examples.domain.Salary;
import examples.entity.Employee;
import examples.entity.JobType;

public class InsertTest {

    @Rule
    public final DbResource dbResource = new DbResource();

    private final EmployeeDao dao = new EmployeeDaoImpl();

    @Test
    public void testInsert() throws Exception {
        LocalTransactionManager tx = AppConfig.singleton()
                .getLocalTransactionManager();

        tx.required(() -> {
            Employee employee = new Employee();
            employee.setName("test");
            employee.setAge(50);
            employee.setSalary(new Salary(300));
            employee.setJobType(JobType.PRESIDENT);
            dao.insert(employee);
        });
    }

    @Test
    public void testInsertWithSqlFile() throws Exception {
        LocalTransactionManager tx = AppConfig.singleton()
                .getLocalTransactionManager();

        tx.required(() -> {
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
        });
    }
}
