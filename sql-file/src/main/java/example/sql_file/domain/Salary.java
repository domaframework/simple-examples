package example.sql_file.domain;

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
    final int prime = 31;
    int result = 1;
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
    Salary other = (Salary) obj;
    if (value == null) {
      return other.value == null;
    } else return value.equals(other.value);
  }

  @Override
  public String toString() {
    return value != null ? String.valueOf(value) : null;
  }
}
