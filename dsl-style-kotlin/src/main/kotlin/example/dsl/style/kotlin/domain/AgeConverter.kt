package example.dsl.style.kotlin.domain

import org.seasar.doma.ExternalDomain
import org.seasar.doma.jdbc.domain.DomainConverter

@ExternalDomain
class AgeConverter : DomainConverter<Age?, Int?> {
    override fun fromDomainToValue(age: Age?): Int? {
        return age?.value
    }

    override fun fromValueToDomain(value: Int?): Age? {
        return value?.let { Age(it) }
    }
}
