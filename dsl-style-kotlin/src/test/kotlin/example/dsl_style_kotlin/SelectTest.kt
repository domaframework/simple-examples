package example.dsl_style_kotlin

import example.dsl_style_kotlin.domain.Age
import example.dsl_style_kotlin.domain.Salary
import example.dsl_style_kotlin.entity.Employee
import example.dsl_style_kotlin.repository.EmployeeRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.seasar.doma.jdbc.Config
import java.time.LocalDateTime

@ExtendWith(TestEnvironment::class)
class SelectTest internal constructor(config: Config) {

    private val repository: EmployeeRepository = EmployeeRepository(config)

    @Test
    fun testSimpleSelect() {
        val employee = repository.selectById(1)
        assertNotNull(employee)
    }

    @Test
    fun testConditionalSelect() {
        var list = repository.selectByAgeRange(Age(30), Age(40))
        assertEquals(6, list.size)
        list = repository.selectByAgeRange(Age(30), null)
        assertEquals(12, list.size)
        list = repository.selectByAgeRange(null, Age(40))
        assertEquals(8, list.size)
        list = repository.selectByAgeRange(null, null)
        assertEquals(14, list.size)
    }

    @Test
    fun testConditionalSelect2() {
        var list = repository.selectByName("SMITH")
        assertEquals(1, list.size)
        list = repository.selectByName(null)
        assertEquals(14, list.size)
    }

    @Test
    fun testIsNotEmpty() {
        var list = repository.selectByNotEmptyName("SMITH")
        assertEquals(1, list.size)
        list = repository.selectByNotEmptyName(null)
        assertEquals(14, list.size)
        list = repository.selectByNotEmptyName("")
        assertEquals(14, list.size)
        list = repository.selectByNotEmptyName("    ")
        assertEquals(0, list.size)
    }

    @Test
    fun testLikePredicate_prefix() {
        val list = repository.selectByNameWithPrefixMatching("S")
        assertEquals(2, list.size)
    }

    @Test
    fun testLikePredicate_suffix() {
        val list = repository.selectByNameWithSuffixMatching("S")
        assertEquals(3, list.size)
    }

    @Test
    fun testLikePredicate_inside() {
        val list = repository.selectByNameWithInfixMatching("A")
        assertEquals(7, list.size)
    }

    @Test
    fun testInPredicate() {
        val names = listOf("JONES", "SCOTT", "XXX")
        val list = repository.selectByNames(names)
        assertEquals(2, list.size)
    }

    @Test
    fun testInPredicate_Domain() {
        val ages = listOf(30, 40, 50, 60).map { Age(it) }
        val list = repository.selectByAges(ages)
        assertEquals(3, list.size)
    }

    @Test
    fun testSelectByTimestampRange() {
        val from = LocalDateTime.parse("2008-01-20T12:34:56")
        val to = LocalDateTime.parse("2008-03-20T12:34:56")
        val list = repository.selectByHiredateRange(from, to)
        assertEquals(3, list.size)
    }

    @Test
    fun testSelectByDomain() {
        val list = repository.selectBySalary(Salary(2900))
        assertEquals(4, list.size)
    }

    @Test
    fun testSelectDomain() {
        val salary = repository.selectSummedSalary()
        assertNotNull(salary)
    }

    @Test
    fun testSelectByEntity() {
        val employee = Employee()
        employee.name = "SMITH"
        val list = repository.selectByExample(employee)
        assertEquals(1, list.size)
    }

    @Test
    fun testStream() {
        val sum = repository.selectByAge(Age(30)) { sequence ->
            sequence.mapNotNull { it.salary }.reduce { acc, salary -> acc.add(salary) }
        }
        assertEquals(Integer.valueOf(21975), sum.value)
    }

    @Test
    fun testOffsetLimit() {
        val list = repository.select(5, 3)
        assertEquals(3, list.size)
    }

    @Test
    fun testCount() {
        val (list, count) = repository.selectAndCount(5, 3)
        assertEquals(3, list.size)
        assertEquals(14, count)
    }

    @Test
    fun testSelectByDepartmentName_in() {
        val list = repository.selectByDepartmentName_in("SALES")
        assertEquals(4, list.size)
    }

    @Test
    fun testSelectByDepartmentName_exists() {
        val list = repository.selectByDepartmentName_exists("SALES")
        assertEquals(4, list.size)
    }

    @Test
    fun testSelectByDepartmentName_join() {
        val list = repository.selectByDepartmentName_join("SALES")
        assertEquals(4, list.size)
    }

    @Test
    fun testAssociation() {
        val list = repository.selectAllWithAssociation()
        assertEquals(14, list.size)
        for (e in list) {
            assertNotNull(e.department?.name)
        }
    }
}
