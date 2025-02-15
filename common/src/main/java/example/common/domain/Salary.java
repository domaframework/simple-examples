package example.common.domain;

import org.seasar.doma.DataType;

@DataType
public record Salary(Integer value) {

  public Salary add(Salary salary) {
    if (salary == null) {
      throw new NullPointerException("The salary parameter is null.");
    }
    if (this.value == null || salary.value == null) {
      return new Salary(null);
    }
    return new Salary(this.value + salary.value);
  }

  public Salary subtract(Salary salary) {
    if (salary == null) {
      throw new NullPointerException("The salary parameter is null.");
    }
    if (this.value == null || salary.value == null) {
      return new Salary(null);
    }
    return new Salary(this.value - salary.value);
  }
}
