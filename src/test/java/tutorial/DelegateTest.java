package tutorial;

import org.seasar.doma.jdbc.tx.LocalTransactionManager;

import tutorial.dao.EmployeeDao;
import tutorial.dao.EmployeeDaoImpl;

public class DelegateTest extends TutorialTestCase {

    private final EmployeeDao dao = new EmployeeDaoImpl();

    public void testDelegate() throws Exception {
        LocalTransactionManager tx = AppConfig.getLocalTransactionManager();
        tx.required(() -> {
            assertEquals(14, dao.count());
        });
    }

}
