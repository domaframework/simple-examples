package example.dsl_style_kotlin.repository

import example.dsl_style_kotlin.domain.Age
import example.dsl_style_kotlin.domain.Salary
import example.dsl_style_kotlin.entity.Department_
import example.dsl_style_kotlin.entity.Employee
import example.dsl_style_kotlin.entity.Employee_
import org.seasar.doma.jdbc.Config
import org.seasar.doma.jdbc.criteria.KEntityql
import org.seasar.doma.jdbc.criteria.KNativeSql
import org.seasar.doma.jdbc.criteria.expression.KExpressions
import org.seasar.doma.jdbc.criteria.option.LikeOption
import java.time.LocalDateTime

class EmployeeRepository(config: Config) {

    private val entityql: KEntityql = KEntityql(config)
    private val nativeSql: KNativeSql = KNativeSql(config)

    fun selectById(id: Int): Employee {
        val e = Employee_()
        return entityql.from(e).where { eq(e.id, id) }.fetchOne()
    }

    fun selectByAgeRange(min: Age?, max: Age?): List<Employee> {
        val e = Employee_()
        return entityql
            .from(e)
            .where {
                ge(e.age, min)
                le(e.age, max)
            }
            .fetch()
    }

    fun selectByName(name: String?): List<Employee> {
        val e = Employee_()
        return entityql.from(e).where { eq(e.name, name) }.fetch()
    }

    fun selectByNames(names: List<String>?): List<Employee> {
        val e = Employee_()
        return entityql.from(e).where { `in`(e.name, names) }.fetch()
    }

    fun selectByAges(ages: List<Age>?): List<Employee> {
        val e = Employee_()
        return entityql.from(e).where { `in`(e.age, ages) }.fetch()
    }

    fun selectByNotEmptyName(name: String?): List<Employee> {
        val e = Employee_()
        return entityql
            .from(e)
            .where {
                if (name != null && name.isNotEmpty()) {
                    eq(e.name, name)
                }
            }
            .fetch()
    }

    fun selectByNameWithPrefixMatching(prefix: String?): List<Employee> {
        val e = Employee_()
        return entityql.from(e).where { like(e.name, prefix, LikeOption.prefix()) }.fetch()
    }

    fun selectByNameWithSuffixMatching(suffix: String?): List<Employee> {
        val e = Employee_()
        return entityql.from(e).where { like(e.name, suffix, LikeOption.suffix()) }.fetch()
    }

    fun selectByNameWithInfixMatching(inside: String?): List<Employee> {
        val e = Employee_()
        return entityql.from(e).where { like(e.name, inside, LikeOption.infix()) }.fetch()
    }

    fun selectByHiredateRange(from: LocalDateTime, to: LocalDateTime): List<Employee> {
        val fromDate = from.toLocalDate()
        val toDate = to.toLocalDate().plusDays(1)
        val e = Employee_()
        return entityql
            .from(e)
            .where {
                ge(e.hiredate, fromDate)
                lt(e.hiredate, toDate)
            }
            .fetch()
    }

    fun selectBySalary(salary: Salary): List<Employee> {
        val e = Employee_()
        return entityql.from(e).where { gt(e.salary, salary) }.fetch()
    }

    fun selectSummedSalary(): Salary? {
        val e = Employee_()
        return nativeSql.from(e).select(KExpressions.sum(e.salary)).fetchOneOrNull()
    }

    fun selectByExample(employee: Employee): List<Employee> {
        val e = Employee_()
        return entityql.from(e).where { eq(e.name, employee.name) }.fetch()
    }

    fun selectAll(): List<Employee> {
        return select(null, null)
    }

    fun selectAndCount(offset: Int?, limit: Int?): Pair<List<Employee>, Long> {
        val list = select(offset, limit)
        val e = Employee_()
        val count = nativeSql.from(e).select(KExpressions.count()).fetchOneOrNull() ?: 0L
        return list to count
    }

    fun select(offset: Int?, limit: Int?): List<Employee> {
        val e = Employee_()
        return nativeSql.from(e).orderBy { asc(e.id) }.offset(offset).limit(limit).fetch()
    }

    fun <R> selectByAge(age: Age, mapper: (Sequence<Employee>) -> R): R {
        val e = Employee_()
        val stmt = nativeSql.from(e).where { gt(e.age, age) }.orderBy { asc(e.age) }
        return stmt.mapSequence(mapper)
    }

    fun selectCount(): Long {
        val e = Employee_()
        return nativeSql.from(e).select(KExpressions.count()).fetchOneOrNull() ?: 0L
    }

    fun selectAllWithAssociation(): List<Employee> {
        val e = Employee_()
        val d = Department_()
        return entityql
            .from(e)
            .leftJoin(d) { eq(e.departmentId, d.id) }
            .orderBy { asc(e.id) }
            .associate(e, d) { employee, department -> employee.department = department }
            .fetch()
    }

    fun insert(employee: Employee) {
        val e = Employee_()
        entityql.insert(e, employee).execute()
    }

    fun insertByNativeSql(employee: Employee): Int {
        val e = Employee_()
        return nativeSql
            .insert(e)
            .values {
                value(e.id, employee.id)
                value(e.name, employee.name)
                value(e.age, employee.age)
                value(e.departmentId, employee.departmentId)
                value(e.hiredate, employee.hiredate)
                value(e.jobType, employee.jobType)
                value(e.salary, employee.salary)
                value(e.insertTimestamp, employee.insertTimestamp)
                value(e.updateTimestamp, employee.updateTimestamp)
                value(e.version, employee.version)
            }
            .execute()
    }

    fun update(employee: Employee) {
        val e = Employee_()
        entityql.update(e, employee).execute()
    }

    fun updateByNativeSql(employee: Employee): Int {
        val e = Employee_()
        return nativeSql
            .update(e)
            .set {
                value(e.name, employee.name)
                value(e.age, employee.age)
                value(e.departmentId, employee.departmentId)
                value(e.hiredate, employee.hiredate)
                value(e.jobType, employee.jobType)
                value(e.salary, employee.salary)
                value(e.updateTimestamp, employee.updateTimestamp)
                value(e.version, employee.version)
            }
            .where { eq(e.id, employee.id) }
            .execute()
    }

    fun delete(employee: Employee) {
        val e = Employee_()
        entityql.delete(e, employee).execute()
    }

    fun deleteByNativeSql(employee: Employee): Int {
        val e = Employee_()
        return nativeSql.delete(e).where { eq(e.id, employee.id) }.execute()
    }

    fun batchInsert(employees: List<Employee>) {
        val e = Employee_()
        val stmt = entityql.insert(e, employees)
        stmt.execute()
    }

    fun batchUpdate(employees: List<Employee>) {
        val e = Employee_()
        entityql.update(e, employees).execute()
    }

    fun batchDelete(employees: List<Employee>) {
        val e = Employee_()
        entityql.delete(e, employees).execute()
    }
}
