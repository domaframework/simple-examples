package example.dsl_style_kotlin

import example.dsl_style_kotlin.domain.Age
import example.dsl_style_kotlin.domain.Salary
import example.dsl_style_kotlin.entity.Employee
import example.dsl_style_kotlin.entity.JobType
import example.dsl_style_kotlin.repository.EmployeeRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.seasar.doma.jdbc.Config
import java.sql.Timestamp

@ExtendWith(TestEnvironment::class)
class InsertTest internal constructor(config: Config) {

    private val repository: EmployeeRepository = EmployeeRepository(config)

    @Test
    fun testInsert() {
        val employee = Employee()
        employee.name = "test"
        employee.age = Age(50)
        employee.salary = Salary(300)
        employee.jobType = JobType.PRESIDENT
        repository.insert(employee)
    }

    @Test
    fun testInsertByNativeSql() {
        val employee = Employee()
        employee.id = 100
        employee.name = "test"
        employee.age = Age(50)
        employee.salary = Salary(300)
        employee.jobType = JobType.PRESIDENT
        employee.insertTimestamp = Timestamp(System.currentTimeMillis())
        employee.version = 1
        repository.insertByNativeSql(employee)
    }
}
