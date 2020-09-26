package example.dsl_style_kotlin.entity

import example.dsl_style_kotlin.domain.Age
import example.dsl_style_kotlin.domain.Salary
import org.seasar.doma.Column
import org.seasar.doma.Entity
import org.seasar.doma.GeneratedValue
import org.seasar.doma.GenerationType
import org.seasar.doma.Id
import org.seasar.doma.Metamodel
import org.seasar.doma.OriginalStates
import org.seasar.doma.SequenceGenerator
import org.seasar.doma.Transient
import org.seasar.doma.Version
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(listener = EmployeeListener::class, metamodel = Metamodel())
class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "EMPLOYEE_SEQ")
    var id: Int? = null
    var name: String? = null
    var age: Age? = null
    var salary: Salary? = null

    @Column(name = "JOB_TYPE")
    var jobType: JobType? = null
    var hiredate: LocalDate? = null

    @Column(name = "DEPARTMENT_ID")
    var departmentId: Int? = null

    @Version
    @Column(name = "VERSION")
    var version: Int? = null
    var insertTimestamp: LocalDateTime? = null
    var updateTimestamp: LocalDateTime? = null

    @Transient
    var department: Department? = null

    @OriginalStates
    var originalStates: Employee? = null

    override fun toString(): String {
        return (
            "Employee{" +
                "id=" +
                id +
                ", name='" +
                name +
                '\'' +
                ", age=" +
                age +
                ", salary=" +
                salary +
                ", jobType=" +
                jobType +
                ", hiredate=" +
                hiredate +
                ", departmentId=" +
                departmentId +
                ", version=" +
                version +
                ", insertTimestamp=" +
                insertTimestamp +
                ", updateTimestamp=" +
                updateTimestamp +
                ", department=" +
                department +
                '}'
            )
    }
}
