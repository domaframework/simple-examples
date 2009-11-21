package tutorial;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.seasar.doma.jdbc.IterationCallback;
import org.seasar.doma.jdbc.IterationContext;

import tutorial.dao.EmployeeDao;
import tutorial.dao.EmployeeDaoImpl;
import tutorial.domain.Salary;
import tutorial.entity.Employee;
import tutorial.entity.JobType;

public class SelectTest extends TutorialTestCase {

    private final EmployeeDao dao = new EmployeeDaoImpl();

    public void testSimpleBinding() {
        Employee employee = dao.selectById(1);
        assertNotNull(employee);
    }

    public void testConditinalBinding() {
        List<Employee> list = dao.selectByAgeRange(30, 40);
        list = dao.selectByAgeRange(30, null);
        assertEquals(12, list.size());
        list = dao.selectByAgeRange(null, 40);
        assertEquals(8, list.size());
        list = dao.selectByAgeRange(null, null);
        assertEquals(14, list.size());
    }

    public void testLikeOperand_prefix() throws Exception {
        List<Employee> list = dao.selectByNamePrefix("S");
        assertEquals(2, list.size());
    }

    public void testLikeOperand_suffix() throws Exception {
        List<Employee> list = dao.selectByNameSuffix("S");
        assertEquals(3, list.size());
    }

    public void testLikeOperand_fuzzy() throws Exception {
        List<Employee> list = dao.selectByFuzzyName("A");
        assertEquals(7, list.size());
    }

    public void testInOperand() throws Exception {
        List<String> names = Arrays.asList("JONES", "SCOTT", "XXX");
        List<Employee> list = dao.selectByNames(names);
        assertEquals(2, list.size());
    }

    public void testSelectByTimestampRange() throws Exception {
        Timestamp from = Timestamp.valueOf("2008-01-20 12:34:56");
        Timestamp to = Timestamp.valueOf("2008-03-20 12:34:56");
        List<Employee> list = dao.selectByHiredateRange(from, to);
        assertEquals(3, list.size());
    }

    public void testSelectByEnum() throws Exception {
        List<Employee> list = dao.selectByJobType(JobType.PRESIDENT);
        assertEquals(1, list.size());
    }

    public void testSelectEnumList() throws Exception {
        List<JobType> list = dao.selectAllJobTypes();
        assertEquals(14, list.size());
    }

    public void testSelectByDomain() throws Exception {
        List<Employee> list = dao.selectBySalary(new Salary(2900));
        assertEquals(4, list.size());
    }

    public void testSelectDomain() throws Exception {
        Salary salary = dao.selectSummedSalary();
        assertNotNull(salary);
    }

    public void testSelectByEntity() throws Exception {
        Employee e = new Employee();
        e.setName("SMITH");
        List<Employee> list = dao.selectByExample(e);
        assertEquals(1, list.size());
    }

    public void testIterate() throws Exception {
        Salary sum = dao.selectAll(new IterationCallback<Salary, Employee>() {

            private Salary sum = new Salary(0);

            @Override
            public Salary iterate(Employee target, IterationContext context) {
                Salary salary = target.getSalary();
                if (salary != null) {
                    sum = sum.add(salary);
                }
                return sum;
            }
        });
        assertEquals(new Integer(29025), sum.getValue());
    }

    public void testIterate_exit() throws Exception {
        Salary sum = dao.selectAll(new IterationCallback<Salary, Employee>() {

            private Salary sum = new Salary(0);

            @Override
            public Salary iterate(Employee target, IterationContext context) {
                Salary salary = target.getSalary();
                if (salary != null) {
                    sum = sum.add(salary);
                }
                if (sum.getValue() != null && sum.getValue() > 10000) {
                    context.exit();
                }
                return sum;
            }
        });
        assertEquals(new Integer(12375), sum.getValue());
    }
}
