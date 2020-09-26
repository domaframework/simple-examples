package example.dao_style_basic.domain;

import org.seasar.doma.ExternalDomain;
import org.seasar.doma.jdbc.domain.DomainConverter;

@ExternalDomain
public class AgeConverter implements DomainConverter<Age, Integer> {
  @Override
  public Integer fromDomainToValue(Age age) {
    return age.getValue();
  }

  @Override
  public Age fromValueToDomain(Integer value) {
    return value == null ? null : new Age(value);
  }
}
