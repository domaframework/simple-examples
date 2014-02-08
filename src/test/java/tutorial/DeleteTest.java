package tutorial;

import org.seasar.doma.jdbc.tx.LocalTransaction;

import tutorial.dao.EmployeeDao;
import tutorial.dao.EmployeeDaoImpl;
import tutorial.entity.Employee;

public class DeleteTest extends TutorialTestCase {

    private final EmployeeDao dao = new EmployeeDaoImpl();

    public void testDelete() throws Exception {
        LocalTransaction tx = AppConfig.getLocalTransaction();
        try {
            tx.begin();

            Employee employee = dao.selectById(1);
            dao.delete(employee);

            tx.commit();
        } finally {
            tx.rollback();
        }
    }

    public void testDeleteWithSqlFile() throws Exception {
        LocalTransaction tx = AppConfig.getLocalTransaction();
        try {
            tx.begin();

            Employee employee = dao.selectById(1);
            dao.deleteWithSqlFile(employee);

            tx.commit();
        } finally {
            tx.rollback();
        }
    }
}
