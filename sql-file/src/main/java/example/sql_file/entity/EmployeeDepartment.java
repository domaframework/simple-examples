package example.sql_file.entity;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;

@Entity
public class EmployeeDepartment extends Employee {

  @Column(name = "DEPARTMENT_NAME")
  String departmentName;

  public String getDepartmentName() {
    return departmentName;
  }

  public void setDepartmentName(String departmentName) {
    this.departmentName = departmentName;
  }
}
