package tutorial;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.seasar.doma.jdbc.SelectOptions;
import org.seasar.doma.jdbc.tx.LocalTransactionManager;

import tutorial.dao.EmployeeDao;
import tutorial.dao.EmployeeDaoImpl;
import tutorial.domain.Salary;
import tutorial.entity.Employee;
import tutorial.entity.EmployeeDepartment;

public class SelectTest {

    @Rule
    public final DbResource dbResource = new DbResource();

    private final EmployeeDao dao = new EmployeeDaoImpl();

    @Test
    public void testSimpleSelect() {
        LocalTransactionManager tx = AppConfig.singleton()
                .getLocalTransactionManager();

        tx.required(() -> {
            Employee employee = dao.selectById(1);
            assertNotNull(employee);
        });
    }

    @Test
    public void testConditinalSelect() {
        LocalTransactionManager tx = AppConfig.singleton()
                .getLocalTransactionManager();

        tx.required(() -> {
            List<Employee> list = dao.selectByAgeRange(30, 40);
            list = dao.selectByAgeRange(30, null);
            assertEquals(12, list.size());
            list = dao.selectByAgeRange(null, 40);
            assertEquals(8, list.size());
            list = dao.selectByAgeRange(null, null);
            assertEquals(14, list.size());
        });
    }

    @Test
    public void testConditinalSelect2() {
        LocalTransactionManager tx = AppConfig.singleton()
                .getLocalTransactionManager();

        tx.required(() -> {
            List<Employee> list = dao.selectByName("SMITH");
            assertEquals(1, list.size());
            list = dao.selectByName(null);
            assertEquals(0, list.size());
        });
    }

    @Test
    public void testLoopSelect() {
        LocalTransactionManager tx = AppConfig.singleton()
                .getLocalTransactionManager();

        tx.required(() -> {
            List<Integer> ages = Arrays.asList(30, 40, 50, 60);
            List<Employee> list = dao.selectByAges(ages);
            assertEquals(3, list.size());
        });
    }

    @Test
    public void testIsNotEmptyFunction() {
        LocalTransactionManager tx = AppConfig.singleton()
                .getLocalTransactionManager();

        tx.required(() -> {
            List<Employee> list = dao.selectByNotEmptyName("SMITH");
            assertEquals(1, list.size());
            list = dao.selectByNotEmptyName(null);
            assertEquals(14, list.size());
            list = dao.selectByNotEmptyName("");
            assertEquals(14, list.size());
            list = dao.selectByNotEmptyName("    ");
            assertEquals(0, list.size());
        });
    }

    @Test
    public void testLikePredicate_prefix() throws Exception {
        LocalTransactionManager tx = AppConfig.singleton()
                .getLocalTransactionManager();

        tx.required(() -> {
            List<Employee> list = dao.selectByNameWithPrefixMatching("S");
            assertEquals(2, list.size());
        });
    }

    @Test
    public void testLikePredicate_suffix() throws Exception {
        LocalTransactionManager tx = AppConfig.singleton()
                .getLocalTransactionManager();

        tx.required(() -> {
            List<Employee> list = dao.selectByNameWithSuffixMatching("S");
            assertEquals(3, list.size());
        });
    }

    @Test
    public void testLikePredicate_inside() throws Exception {
        LocalTransactionManager tx = AppConfig.singleton()
                .getLocalTransactionManager();

        tx.required(() -> {
            List<Employee> list = dao.selectByNameWithInsideMatching("A");
            assertEquals(7, list.size());
        });
    }

    @Test
    public void testInPredicate() throws Exception {
        LocalTransactionManager tx = AppConfig.singleton()
                .getLocalTransactionManager();

        tx.required(() -> {
            List<String> names = Arrays.asList("JONES", "SCOTT", "XXX");
            List<Employee> list = dao.selectByNames(names);
            assertEquals(2, list.size());
        });
    }

    @Test
    public void testSelectByTimestampRange() throws Exception {
        LocalTransactionManager tx = AppConfig.singleton()
                .getLocalTransactionManager();

        tx.required(() -> {
            Timestamp from = Timestamp.valueOf("2008-01-20 12:34:56");
            Timestamp to = Timestamp.valueOf("2008-03-20 12:34:56");
            List<Employee> list = dao.selectByHiredateRange(from, to);
            assertEquals(3, list.size());
        });
    }

    @Test
    public void testSelectByDomain() throws Exception {
        LocalTransactionManager tx = AppConfig.singleton()
                .getLocalTransactionManager();

        tx.required(() -> {
            List<Employee> list = dao.selectBySalary(new Salary(2900));
            assertEquals(4, list.size());
        });
    }

    @Test
    public void testSelectDomain() throws Exception {
        LocalTransactionManager tx = AppConfig.singleton()
                .getLocalTransactionManager();

        tx.required(() -> {
            Salary salary = dao.selectSummedSalary();
            assertNotNull(salary);
        });
    }

    @Test
    public void testSelectByEntity() throws Exception {
        LocalTransactionManager tx = AppConfig.singleton()
                .getLocalTransactionManager();

        tx.required(() -> {
            Employee e = new Employee();
            e.setName("SMITH");
            List<Employee> list = dao.selectByExample(e);
            assertEquals(1, list.size());
        });
    }

    @Test
    public void testStream() throws Exception {
        LocalTransactionManager tx = AppConfig.singleton()
                .getLocalTransactionManager();
        tx.required(() -> {
            Salary sum = dao.selectByAge(
                    30,
                    s -> s.map(employee -> employee.getSalary())
                            .filter(salary -> salary != null)
                            .reduce(new Salary(0), (x, y) -> x.add(y)));
            assertEquals(new Integer(21975), sum.getValue());
        });
    }

    @Test
    public void testOffsetLimit() throws Exception {
        LocalTransactionManager tx = AppConfig.singleton()
                .getLocalTransactionManager();

        tx.required(() -> {
            SelectOptions options = SelectOptions.get().offset(5).limit(3);
            List<Employee> list = dao.selectAll(options);
            assertEquals(3, list.size());
        });
    }

    @Test
    public void testCount() throws Exception {
        LocalTransactionManager tx = AppConfig.singleton()
                .getLocalTransactionManager();

        tx.required(() -> {
            SelectOptions options = SelectOptions.get().offset(5).limit(3)
                    .count();
            List<Employee> list = dao.selectAll(options);
            assertEquals(3, list.size());
            assertEquals(14, options.getCount());
        });
    }

    @Test
    public void testSelectJoinedResult() throws Exception {
        LocalTransactionManager tx = AppConfig.singleton()
                .getLocalTransactionManager();

        tx.required(() -> {
            List<EmployeeDepartment> list = dao.selectAllEmployeeDepartment();
            assertEquals(14, list.size());
            for (EmployeeDepartment e : list) {
                assertNotNull(e.getDepartmentName());
            }
        });
    }
}
