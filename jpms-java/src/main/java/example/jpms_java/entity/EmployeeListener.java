package example.jpms_java.entity;

import java.time.LocalDateTime;
import org.seasar.doma.jdbc.entity.EntityListener;
import org.seasar.doma.jdbc.entity.PostDeleteContext;
import org.seasar.doma.jdbc.entity.PostInsertContext;
import org.seasar.doma.jdbc.entity.PostUpdateContext;
import org.seasar.doma.jdbc.entity.PreDeleteContext;
import org.seasar.doma.jdbc.entity.PreInsertContext;
import org.seasar.doma.jdbc.entity.PreUpdateContext;

public class EmployeeListener<E extends Employee> implements EntityListener<E> {

  @Override
  public void preDelete(E employee, PreDeleteContext<E> context) {}

  @Override
  public void preInsert(E employee, PreInsertContext<E> context) {
    LocalDateTime timestamp = LocalDateTime.now();
    employee.setInsertTimestamp(timestamp);
  }

  @Override
  public void preUpdate(E employee, PreUpdateContext<E> context) {
    if (context.isEntityChanged()) {
      LocalDateTime timestamp = LocalDateTime.now();
      employee.setUpdateTimestamp(timestamp);
    }
  }

  @Override
  public void postInsert(E entity, PostInsertContext<E> context) {}

  @Override
  public void postUpdate(E entity, PostUpdateContext<E> context) {}

  @Override
  public void postDelete(E entity, PostDeleteContext<E> context) {}
}
