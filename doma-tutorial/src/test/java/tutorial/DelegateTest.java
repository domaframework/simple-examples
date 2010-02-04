package tutorial;

import tutorial.dao.EmployeeDao;
import tutorial.dao.EmployeeDaoImpl;

public class DelegateTest extends TutorialTestCase {

    private final EmployeeDao dao = new EmployeeDaoImpl();

    public void testDelegate() throws Exception {
        assertEquals(14, dao.count());
    }

}
