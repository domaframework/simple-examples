package tutorial;

import java.util.List;

import org.seasar.doma.jdbc.tx.LocalTransaction;

import tutorial.dao.EmployeeDao;
import tutorial.dao.EmployeeDaoImpl;
import tutorial.entity.Employee;

public class BatchDeleteTest extends TutorialTestCase {

    private final EmployeeDao dao = new EmployeeDaoImpl();

    public void testBatchDelete() throws Exception {
        LocalTransaction tx = AppConfig.getLocalTransaction();
        try {
            tx.begin();

            List<Employee> list = dao.selectAll();
            dao.batchDelete(list);

            tx.commit();
        } finally {
            tx.rollback();
        }
    }
}
