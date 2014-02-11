package tutorial;

import org.seasar.doma.jdbc.tx.LocalTransactionManager;

import tutorial.dao.EmployeeDao;
import tutorial.dao.EmployeeDaoImpl;
import tutorial.entity.Employee;

public class DeleteTest extends TutorialTestCase {

    private final EmployeeDao dao = new EmployeeDaoImpl();

    public void testDelete() throws Exception {
        LocalTransactionManager tx = AppConfig.getLocalTransactionManager();
        tx.required(() -> {
            Employee employee = dao.selectById(1);
            dao.delete(employee);
        });

    }

    public void testDeleteWithSqlFile() throws Exception {
        LocalTransactionManager tx = AppConfig.getLocalTransactionManager();
        tx.required(() -> {
            Employee employee = dao.selectById(1);
            dao.deleteWithSqlFile(employee);
        });
    }
}
