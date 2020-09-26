package example.dsl_style_kotlin

import example.dsl_style_kotlin.repository.EmployeeRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.seasar.doma.jdbc.Config

@ExtendWith(TestEnvironment::class)
class BatchDeleteTest internal constructor(config: Config) {

    private val repository: EmployeeRepository = EmployeeRepository(config)

    @Test
    fun testBatchDelete() {
        val list = repository.selectAll()
        repository.batchDelete(list)
    }
}
