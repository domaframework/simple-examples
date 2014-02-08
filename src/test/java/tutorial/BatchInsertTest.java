package tutorial;

import java.util.Arrays;

import org.seasar.doma.jdbc.tx.LocalTransaction;

import tutorial.dao.EmployeeDao;
import tutorial.dao.EmployeeDaoImpl;
import tutorial.domain.Salary;
import tutorial.entity.Employee;

public class BatchInsertTest extends TutorialTestCase {

    private final EmployeeDao dao = new EmployeeDaoImpl();

    public void testBatchInsert() throws Exception {
        LocalTransaction tx = AppConfig.getLocalTransaction();
        try {
            tx.begin();

            Employee employee1 = new Employee();
            employee1.setName("test-1");
            employee1.setAge(30);
            employee1.setSalary(new Salary(300));

            Employee employee2 = new Employee();
            employee2.setName("test-2");
            employee2.setAge(40);
            employee2.setSalary(new Salary(500));

            dao.batchInsert(Arrays.asList(employee1, employee2));

            tx.commit();
        } finally {
            tx.rollback();
        }
    }
}
