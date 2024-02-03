package example.dsl.style.kotlin

import example.dsl.style.kotlin.repository.EmployeeRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.seasar.doma.jdbc.Config

@ExtendWith(TestEnvironment::class)
class AggregateFunctionTest internal constructor(config: Config) {

    private val repository: EmployeeRepository = EmployeeRepository(config)

    @Test
    fun testCount() {
        assertEquals(14L, repository.selectCount())
    }
}
