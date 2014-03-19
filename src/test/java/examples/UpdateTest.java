package examples;

import org.junit.Rule;
import org.junit.Test;
import org.seasar.doma.jdbc.tx.LocalTransactionManager;

import examples.AppConfig;
import examples.dao.EmployeeDao;
import examples.dao.EmployeeDaoImpl;
import examples.entity.Employee;
import examples.entity.JobType;

public class UpdateTest {

    @Rule
    public final DbResource dbResource = new DbResource();

    private final EmployeeDao dao = new EmployeeDaoImpl();

    @Test
    public void testUpdate() throws Exception {
        LocalTransactionManager tx = AppConfig.singleton()
                .getLocalTransactionManager();

        tx.required(() -> {
            Employee employee = dao.selectById(1);
            employee.setName("hoge");
            employee.setJobType(JobType.PRESIDENT);
            dao.update(employee);
        });
    }

    @Test
    public void testUpdateWithSqlFile() throws Exception {
        LocalTransactionManager tx = AppConfig.singleton()
                .getLocalTransactionManager();

        tx.required(() -> {
            Employee employee = dao.selectById(1);
            employee.setName("hoge");
            employee.setJobType(JobType.PRESIDENT);
            dao.updateWithSqlFile(employee);
        });
    }
}
