package example.dsl.style.kotlin.domain

import org.seasar.doma.Domain

@Domain(valueType = Int::class)
data class Salary(val value: Int) {

    fun add(salary: Salary): Salary {
        return Salary(value + salary.value)
    }
}
