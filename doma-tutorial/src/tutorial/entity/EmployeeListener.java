package tutorial.entity;

import java.sql.Timestamp;

import org.seasar.doma.jdbc.entity.EntityListener;

public class EmployeeListener implements EntityListener<Employee> {

    @Override
    public void preDelete(Employee employee) {
    }

    @Override
    public void preInsert(Employee employee) {
        employee.setInsertTimestamp(new Timestamp(System.currentTimeMillis()));
    }

    @Override
    public void preUpdate(Employee employee) {
        employee.setUpdateTimestamp(new Timestamp(System.currentTimeMillis()));
    }

}
