package example.dsl_style_kotlin

import example.dsl_style_kotlin.domain.Age
import example.dsl_style_kotlin.domain.Salary
import example.dsl_style_kotlin.entity.Employee
import example.dsl_style_kotlin.repository.EmployeeRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.seasar.doma.jdbc.Config

@ExtendWith(TestEnvironment::class)
class BatchInsertTest internal constructor(config: Config) {

    private val repository: EmployeeRepository = EmployeeRepository(config)

    @Test
    fun testBatchInsert() {
        val employee1 = Employee()
        employee1.name = "test-1"
        employee1.age = Age(30)
        employee1.salary = Salary(300)
        val employee2 = Employee()
        employee2.name = "test-2"
        employee2.age = Age(40)
        employee2.salary = Salary(500)
        repository.batchInsert(listOf(employee1, employee2))
    }
}
