package tutorial;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.seasar.doma.jdbc.IterationCallback;
import org.seasar.doma.jdbc.IterationContext;
import org.seasar.doma.jdbc.PostIterationCallback;
import org.seasar.doma.jdbc.SelectOptions;
import org.seasar.doma.jdbc.tx.LocalTransaction;

import tutorial.dao.EmployeeDao;
import tutorial.dao.EmployeeDaoImpl;
import tutorial.domain.Salary;
import tutorial.entity.Employee;
import tutorial.entity.EmployeeDepartment;

public class SelectTest extends TutorialTestCase {

    private final EmployeeDao dao = new EmployeeDaoImpl();

    public void testSimpleSelect() {
        LocalTransaction tx = AppConfig.getLocalTransaction();
        try {
            tx.begin();

            Employee employee = dao.selectById(1);
            assertNotNull(employee);

            tx.commit();
        } finally {
            tx.rollback();
        }
    }

    public void testConditinalSelect() {
        LocalTransaction tx = AppConfig.getLocalTransaction();
        try {
            tx.begin();

            List<Employee> list = dao.selectByAgeRange(30, 40);
            list = dao.selectByAgeRange(30, null);
            assertEquals(12, list.size());
            list = dao.selectByAgeRange(null, 40);
            assertEquals(8, list.size());
            list = dao.selectByAgeRange(null, null);
            assertEquals(14, list.size());

            tx.commit();
        } finally {
            tx.rollback();
        }
    }

    public void testConditinalSelect2() {
        LocalTransaction tx = AppConfig.getLocalTransaction();
        try {
            tx.begin();

            List<Employee> list = dao.selectByName("SMITH");
            assertEquals(1, list.size());
            list = dao.selectByName(null);
            assertEquals(0, list.size());

            tx.commit();
        } finally {
            tx.rollback();
        }
    }

    public void testLoopSelect() {
        LocalTransaction tx = AppConfig.getLocalTransaction();
        try {
            tx.begin();

            List<Integer> ages = Arrays.asList(30, 40, 50, 60);
            List<Employee> list = dao.selectByAges(ages);
            assertEquals(3, list.size());

            tx.commit();
        } finally {
            tx.rollback();
        }
    }

    public void testIsNotEmptyFunction() {
        LocalTransaction tx = AppConfig.getLocalTransaction();
        try {
            tx.begin();

            List<Employee> list = dao.selectByNotEmptyName("SMITH");
            assertEquals(1, list.size());
            list = dao.selectByNotEmptyName(null);
            assertEquals(14, list.size());
            list = dao.selectByNotEmptyName("");
            assertEquals(14, list.size());
            list = dao.selectByNotEmptyName("    ");
            assertEquals(0, list.size());

            tx.commit();
        } finally {
            tx.rollback();
        }
    }

    public void testLikePredicate_prefix() throws Exception {
        LocalTransaction tx = AppConfig.getLocalTransaction();
        try {
            tx.begin();

            List<Employee> list = dao.selectByNameWithPrefixMatching("S");
            assertEquals(2, list.size());

            tx.commit();
        } finally {
            tx.rollback();
        }
    }

    public void testLikePredicate_suffix() throws Exception {
        LocalTransaction tx = AppConfig.getLocalTransaction();
        try {
            tx.begin();

            List<Employee> list = dao.selectByNameWithSuffixMatching("S");
            assertEquals(3, list.size());

            tx.commit();
        } finally {
            tx.rollback();
        }
    }

    public void testLikePredicate_inside() throws Exception {
        LocalTransaction tx = AppConfig.getLocalTransaction();
        try {
            tx.begin();

            List<Employee> list = dao.selectByNameWithInsideMatching("A");
            assertEquals(7, list.size());

            tx.commit();
        } finally {
            tx.rollback();
        }
    }

    public void testInPredicate() throws Exception {
        LocalTransaction tx = AppConfig.getLocalTransaction();
        try {
            tx.begin();

            List<String> names = Arrays.asList("JONES", "SCOTT", "XXX");
            List<Employee> list = dao.selectByNames(names);
            assertEquals(2, list.size());

            tx.commit();
        } finally {
            tx.rollback();
        }
    }

    public void testSelectByTimestampRange() throws Exception {
        LocalTransaction tx = AppConfig.getLocalTransaction();
        try {
            tx.begin();

            Timestamp from = Timestamp.valueOf("2008-01-20 12:34:56");
            Timestamp to = Timestamp.valueOf("2008-03-20 12:34:56");
            List<Employee> list = dao.selectByHiredateRange(from, to);
            assertEquals(3, list.size());

            tx.commit();
        } finally {
            tx.rollback();
        }
    }

    public void testSelectByDomain() throws Exception {
        LocalTransaction tx = AppConfig.getLocalTransaction();
        try {
            tx.begin();

            List<Employee> list = dao.selectBySalary(new Salary(2900));
            assertEquals(4, list.size());

            tx.commit();
        } finally {
            tx.rollback();
        }
    }

    public void testSelectDomain() throws Exception {
        LocalTransaction tx = AppConfig.getLocalTransaction();
        try {
            tx.begin();

            Salary salary = dao.selectSummedSalary();
            assertNotNull(salary);

            tx.commit();
        } finally {
            tx.rollback();
        }
    }

    public void testSelectByEntity() throws Exception {
        LocalTransaction tx = AppConfig.getLocalTransaction();
        try {
            tx.begin();

            Employee e = new Employee();
            e.setName("SMITH");
            List<Employee> list = dao.selectByExample(e);
            assertEquals(1, list.size());

            tx.commit();
        } finally {
            tx.rollback();
        }
    }

    public void testIterate() throws Exception {
        LocalTransaction tx = AppConfig.getLocalTransaction();
        try {
            tx.begin();

            Salary sum = dao.selectByAge(30,
                    new IterationCallback<Salary, Employee>() {

                        private Salary sum = new Salary(0);

                        @Override
                        public Salary iterate(Employee target,
                                IterationContext context) {
                            Salary salary = target.getSalary();
                            if (salary != null) {
                                sum = sum.add(salary);
                            }
                            return sum;
                        }
                    });
            assertEquals(new Integer(21975), sum.getValue());

            tx.commit();
        } finally {
            tx.rollback();
        }
    }

    public void testIterate_exit() throws Exception {
        LocalTransaction tx = AppConfig.getLocalTransaction();
        try {
            tx.begin();

            Salary sum = dao.selectByAge(30,
                    new IterationCallback<Salary, Employee>() {

                        private Salary sum = new Salary(0);

                        @Override
                        public Salary iterate(Employee target,
                                IterationContext context) {
                            Salary salary = target.getSalary();
                            if (salary != null) {
                                sum = sum.add(salary);
                            }
                            if (sum.getValue() != null
                                    && sum.getValue() > 10000) {
                                context.exit();
                            }
                            return sum;
                        }
                    });
            assertEquals(new Integer(10725), sum.getValue());

            tx.commit();
        } finally {
            tx.rollback();
        }
    }

    public void testIterate_post() throws Exception {
        LocalTransaction tx = AppConfig.getLocalTransaction();
        try {
            tx.begin();

            Salary sum = dao.selectByAge(30,
                    new PostIterationCallback<Salary, Employee>() {

                        private Salary sum = new Salary(0);

                        @Override
                        public Salary iterate(Employee target,
                                IterationContext context) {
                            Salary salary = target.getSalary();
                            if (salary != null) {
                                sum = sum.add(salary);
                            }
                            return sum;
                        }

                        public Salary postIterate(Salary salary,
                                IterationContext context) {
                            return salary.add(new Salary(10000));
                        }

                    });
            assertEquals(new Integer(31975), sum.getValue());

            tx.commit();
        } finally {
            tx.rollback();
        }
    }

    public void testOffsetLimit() throws Exception {
        LocalTransaction tx = AppConfig.getLocalTransaction();
        try {
            tx.begin();

            SelectOptions options = SelectOptions.get().offset(5).limit(3);
            List<Employee> list = dao.selectAll(options);
            assertEquals(3, list.size());

            tx.commit();
        } finally {
            tx.rollback();
        }
    }

    public void testCount() throws Exception {
        LocalTransaction tx = AppConfig.getLocalTransaction();
        try {
            tx.begin();

            SelectOptions options = SelectOptions.get().offset(5).limit(3)
                    .count();
            List<Employee> list = dao.selectAll(options);
            assertEquals(3, list.size());
            assertEquals(14, options.getCount());

            tx.commit();
        } finally {
            tx.rollback();
        }
    }

    public void testSelectJoinedResult() throws Exception {
        LocalTransaction tx = AppConfig.getLocalTransaction();
        try {
            tx.begin();

            List<EmployeeDepartment> list = dao.selectAllEmployeeDepartment();
            assertEquals(14, list.size());
            for (EmployeeDepartment e : list) {
                assertNotNull(e.getDepartmentName());
            }

            tx.commit();
        } finally {
            tx.rollback();
        }
    }
}
