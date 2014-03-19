package examples;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.seasar.doma.jdbc.tx.LocalTransactionManager;

import examples.AppConfig;
import examples.dao.EmployeeDao;
import examples.dao.EmployeeDaoImpl;

public class DelegateTest {

    @Rule
    public final DbResource dbResource = new DbResource();

    private final EmployeeDao dao = new EmployeeDaoImpl();

    @Test
    public void testDelegate() throws Exception {
        LocalTransactionManager tx = AppConfig.singleton()
                .getLocalTransactionManager();

        tx.required(() -> {
            assertEquals(14, dao.count());
        });
    }

}
