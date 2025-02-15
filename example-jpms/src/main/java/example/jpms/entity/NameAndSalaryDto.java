package example.jpms.entity;

import example.jpms.domain.Salary;

public class NameAndSalaryDto {

  private final String name;
  private final Salary salary;

  public NameAndSalaryDto(String name, Salary salary) {
    this.name = name;
    this.salary = salary;
  }

  public String getName() {
    return name;
  }

  public Salary getSalary() {
    return salary;
  }
}
