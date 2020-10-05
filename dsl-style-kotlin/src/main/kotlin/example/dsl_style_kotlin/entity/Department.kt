package example.dsl_style_kotlin.entity

import org.seasar.doma.Entity
import org.seasar.doma.Id
import org.seasar.doma.Metamodel
import org.seasar.doma.Transient
import org.seasar.doma.Version

@Entity(metamodel = Metamodel())
class Department {
    @Id
    var id: Int? = null
    var name: String? = null
    @Version
    var version: Int? = null
    @Transient
    val employees: MutableList<Employee> = mutableListOf()
}
