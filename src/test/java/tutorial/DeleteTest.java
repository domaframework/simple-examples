package tutorial;

import org.junit.Rule;
import org.junit.Test;
import org.seasar.doma.jdbc.tx.LocalTransactionManager;

import tutorial.dao.EmployeeDao;
import tutorial.dao.EmployeeDaoImpl;
import tutorial.entity.Employee;

public class DeleteTest {

    @Rule
    public final DbResource dbResource = new DbResource();

    private final EmployeeDao dao = new EmployeeDaoImpl();

    @Test
    public void testDelete() throws Exception {
        LocalTransactionManager tx = AppConfig.singleton()
                .getLocalTransactionManager();

        tx.required(() -> {
            Employee employee = dao.selectById(1);
            dao.delete(employee);
        });
    }

    @Test
    public void testDeleteWithSqlFile() throws Exception {
        LocalTransactionManager tx = AppConfig.singleton()
                .getLocalTransactionManager();

        tx.required(() -> {
            Employee employee = dao.selectById(1);
            dao.deleteWithSqlFile(employee);
        });
    }
}
