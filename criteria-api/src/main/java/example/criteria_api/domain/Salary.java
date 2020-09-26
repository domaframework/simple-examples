package example.criteria_api.domain;

import org.seasar.doma.Domain;

@Domain(valueType = Integer.class)
public class Salary {

  private final Integer value;

  public Salary(Integer value) {
    this.value = value;
  }

  public Integer getValue() {
    return value;
  }

  public Salary add(Salary salary) {
    if (salary == null) {
      throw new NullPointerException("The salary parameter is null.");
    }
    if (this.value == null || salary.value == null) {
      return new Salary(null);
    }
    return new Salary(this.value + salary.value);
  }

  @Override
  public int hashCode() {
    final var prime = 31;
    var result = 1;
    result = prime * result + ((value == null) ? 0 : value.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    var other = (Salary) obj;
    if (value == null) {
      return other.value == null;
    } else return value.equals(other.value);
  }

  @Override
  public String toString() {
    return value != null ? String.valueOf(value) : null;
  }
}