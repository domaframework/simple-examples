package example.dsl.style.kotlin

import example.dsl.style.kotlin.entity.JobType
import example.dsl.style.kotlin.repository.EmployeeRepository
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.seasar.doma.jdbc.Config

@ExtendWith(TestEnvironment::class)
class UpdateTest internal constructor(config: Config) {

    private val repository: EmployeeRepository = EmployeeRepository(config)

    @Test
    fun testUpdate() {
        val employee = repository.selectById(1)
        assertNotNull(employee)
        employee.name = "hoge"
        employee.jobType = JobType.PRESIDENT
        repository.update(employee)
    }

    @Test
    fun testUpdateByNativeSql() {
        val employee = repository.selectById(1)
        assertNotNull(employee)
        employee.name = "hoge"
        employee.jobType = JobType.PRESIDENT
        repository.updateByNativeSql(employee)
    }
}
