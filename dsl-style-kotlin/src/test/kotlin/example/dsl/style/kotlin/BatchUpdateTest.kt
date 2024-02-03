package example.dsl.style.kotlin

import example.dsl.style.kotlin.domain.Salary
import example.dsl.style.kotlin.repository.EmployeeRepository
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
