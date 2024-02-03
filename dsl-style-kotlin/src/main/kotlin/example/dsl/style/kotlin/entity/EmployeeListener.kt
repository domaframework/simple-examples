package example.dsl.style.kotlin.entity

import org.seasar.doma.jdbc.entity.EntityListener
import org.seasar.doma.jdbc.entity.PostDeleteContext
import org.seasar.doma.jdbc.entity.PostInsertContext
import org.seasar.doma.jdbc.entity.PostUpdateContext
import org.seasar.doma.jdbc.entity.PreDeleteContext
import org.seasar.doma.jdbc.entity.PreInsertContext
import org.seasar.doma.jdbc.entity.PreUpdateContext
import java.time.LocalDateTime

class EmployeeListener : EntityListener<Employee> {
    override fun preDelete(employee: Employee, context: PreDeleteContext<Employee>) {}
    override fun preInsert(employee: Employee, context: PreInsertContext<Employee>) {
        val timestamp = LocalDateTime.now()
        employee.insertTimestamp = timestamp
    }

    override fun preUpdate(employee: Employee, context: PreUpdateContext<Employee>) {
        if (context.isEntityChanged) {
            val timestamp = LocalDateTime.now()
            employee.updateTimestamp = timestamp
        }
    }

    override fun postInsert(entity: Employee, context: PostInsertContext<Employee>) {}
    override fun postUpdate(entity: Employee, context: PostUpdateContext<Employee>) {}
    override fun postDelete(entity: Employee, context: PostDeleteContext<Employee>) {}
}
