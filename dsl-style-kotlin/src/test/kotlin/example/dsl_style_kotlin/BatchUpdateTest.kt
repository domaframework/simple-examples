package example.dsl_style_kotlin

import example.dsl_style_kotlin.domain.Salary
import example.dsl_style_kotlin.repository.EmployeeRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.seasar.doma.jdbc.Config

@ExtendWith(TestEnvironment::class)
class BatchUpdateTest(config: Config) {

    private val repository: EmployeeRepository = EmployeeRepository(config)

    @Test
    fun testBatchUpdate() {
        val list = repository.selectAll()
        for (employee in list) {
            val salary = employee.salary
            if (salary != null) {
                employee.salary = salary.add(Salary(100))
            }
        }
        repository.batchUpdate(list)
    }
}
