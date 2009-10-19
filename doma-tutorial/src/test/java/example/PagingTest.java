package example;

import java.util.List;
import java.util.logging.Logger;

import org.seasar.doma.jdbc.SelectOptions;

import example.dao.EmployeeDao;
import example.dao.EmployeeDaoImpl;
import example.entity.Employee;

public class PagingTest extends TutorialTestCase {

    private final EmployeeDao dao = new EmployeeDaoImpl();

    public void testPaging() throws Exception {
        SelectOptions options = SelectOptions.get().offset(5).limit(3);
        List<Employee> list = dao.selectAll(options);
        for (Employee employee : list) {
            Logger.getAnonymousLogger().info(employee.toString());
        }
    }

}
