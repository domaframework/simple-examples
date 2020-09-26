package example.dsl_style_kotlin

import example.dsl_style_kotlin.repository.EmployeeRepository
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.seasar.doma.jdbc.Config

@ExtendWith(TestEnvironment::class)
class DeleteTest internal constructor(config: Config) {

    private val repository: EmployeeRepository = EmployeeRepository(config)

    @Test
    fun testDelete() {
        val employee = repository.selectById(1)
        assertNotNull(employee)
        repository.delete(employee)
    }

    @Test
    fun testDeleteByNativeSql() {
        val employee = repository.selectById(1)
        assertNotNull(employee)
        repository.deleteByNativeSql(employee)
    }
}
