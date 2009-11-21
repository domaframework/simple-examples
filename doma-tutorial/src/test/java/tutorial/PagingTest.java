package tutorial;

import java.util.List;

import org.seasar.doma.jdbc.SelectOptions;

import tutorial.dao.EmployeeDao;
import tutorial.dao.EmployeeDaoImpl;
import tutorial.entity.Employee;

public class PagingTest extends TutorialTestCase {

    private final EmployeeDao dao = new EmployeeDaoImpl();

    public void testOffsetLimit() throws Exception {
        SelectOptions options = SelectOptions.get().offset(5).limit(3);
        List<Employee> list = dao.selectAll(options);
        assertEquals(3, list.size());
    }

    public void testCount() throws Exception {
        SelectOptions options = SelectOptions.get().offset(5).limit(3).count();
        List<Employee> list = dao.selectAll(options);
        assertEquals(3, list.size());
        assertEquals(14, options.getCount());
    }
}
