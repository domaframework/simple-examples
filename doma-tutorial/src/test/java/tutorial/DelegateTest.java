package tutorial;

import org.seasar.doma.jdbc.tx.LocalTransaction;

import tutorial.dao.EmployeeDao;
import tutorial.dao.EmployeeDaoImpl;

public class DelegateTest extends TutorialTestCase {

    private final EmployeeDao dao = new EmployeeDaoImpl();

    public void testDelegate() throws Exception {
        LocalTransaction tx = AppConfig.getLocalTransaction();
        try {
            tx.begin();

            assertEquals(14, dao.count());

            tx.commit();
        } finally {
            tx.rollback();
        }
    }

}
