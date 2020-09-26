package example.dsl_style.entity;

import example.dsl_style.domain.Age;
import example.dsl_style.domain.Salary;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Metamodel;
import org.seasar.doma.OriginalStates;
import org.seasar.doma.SequenceGenerator;
import org.seasar.doma.Transient;
import org.seasar.doma.Version;

@Entity(listener = EmployeeListener.class, metamodel = @Metamodel)
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(sequence = "EMPLOYEE_SEQ")
  Integer id;

  String name;

  Age age;

  Salary salary;

  @Column(name = "JOB_TYPE")
  JobType jobType;

  LocalDate hiredate;

  @Column(name = "DEPARTMENT_ID")
  Integer departmentId;

  @Version
  @Column(name = "VERSION")
  Integer version;

  LocalDateTime insertTimestamp;

  LocalDateTime updateTimestamp;

  @Transient Department department;

  @OriginalStates Employee originalStates;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Age getAge() {
    return age;
  }

  public void setAge(Age age) {
    this.age = age;
  }

  public Salary getSalary() {
    return salary;
  }

  public void setSalary(Salary salary) {
    this.salary = salary;
  }

  public JobType getJobType() {
    return jobType;
  }

  public void setJobType(JobType jobType) {
    this.jobType = jobType;
  }

  public LocalDate getHiredate() {
    return hiredate;
  }

  public void setHiredate(LocalDate hiredate) {
    this.hiredate = hiredate;
  }

  public Integer getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(Integer departmentId) {
    this.departmentId = departmentId;
  }

  public LocalDateTime getInsertTimestamp() {
    return insertTimestamp;
  }

  public void setInsertTimestamp(LocalDateTime insertTimestamp) {
    this.insertTimestamp = insertTimestamp;
  }

  public LocalDateTime getUpdateTimestamp() {
    return updateTimestamp;
  }

  public void setUpdateTimestamp(LocalDateTime updateTimestamp) {
    this.updateTimestamp = updateTimestamp;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  @Override
  public String toString() {
    return "Employee{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", age="
        + age
        + ", salary="
        + salary
        + ", jobType="
        + jobType
        + ", hiredate="
        + hiredate
        + ", departmentId="
        + departmentId
        + ", version="
        + version
        + ", insertTimestamp="
        + insertTimestamp
        + ", updateTimestamp="
        + updateTimestamp
        + ", department="
        + department
        + '}';
  }
}
