package tutorial;

import java.util.List;

import org.seasar.doma.jdbc.tx.LocalTransactionManager;

import tutorial.dao.EmployeeDao;
import tutorial.dao.EmployeeDaoImpl;
import tutorial.entity.Employee;

public class BatchDeleteTest extends TutorialTestCase {

    private final EmployeeDao dao = new EmployeeDaoImpl();

    public void testBatchDelete() throws Exception {
        LocalTransactionManager tx = AppConfig.getLocalTransactionManager();
        tx.required(() -> {
            List<Employee> list = dao.selectAll();
            dao.batchDelete(list);
        });
    }
}
