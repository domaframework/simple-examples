package tutorial;

import junit.framework.TestCase;

import org.seasar.doma.jdbc.tx.LocalTransaction;

import tutorial.dao.EmployeeDao;
import tutorial.dao.EmployeeDaoImpl;

public abstract class TutorialTestCase extends TestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        LocalTransaction tx = AppConfig.getLocalTransaction();
        try {
            tx.begin();

            EmployeeDao dao = new EmployeeDaoImpl();
            dao.create();

            tx.commit();
        } finally {
            tx.rollback();
        }
    }

    @Override
    protected void tearDown() throws Exception {
        LocalTransaction tx = AppConfig.getLocalTransaction();
        try {
            tx.begin();

            EmployeeDao dao = new EmployeeDaoImpl();
            dao.drop();

            tx.commit();
        } finally {
            tx.rollback();
        }
        super.tearDown();
    }

}
