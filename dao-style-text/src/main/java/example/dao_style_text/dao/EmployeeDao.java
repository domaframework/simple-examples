package example.dao_style_text.dao;

import example.dao_style_text.domain.Age;
import example.dao_style_text.domain.Salary;
import example.dao_style_text.entity.Department;
import example.dao_style_text.entity.Employee;
import java.sql.Timestamp;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;
import org.seasar.doma.AggregateStrategy;
import org.seasar.doma.AssociationLinker;
import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.SelectType;
import org.seasar.doma.Sql;
import org.seasar.doma.Update;
import org.seasar.doma.jdbc.SelectOptions;

@Dao
public interface EmployeeDao {

  @Sql(
      """
      select
        /*%expand*/*
      from
        employee
      where
        id = /* id */0
      """)
  @Select
  Employee selectById(Integer id);

  @Sql(
      """
      select
        /*%expand*/*
      from
        employee
      where
      /*%if min != null */
        age >= /* min */10
      /*%end */
      /*%if max != null */
        and
        age <= /* max */70
      /*%end */
      order by
        age
      """)
  @Select
  List<Employee> selectByAgeRange(Age min, Age max);

  @Sql(
      """
      select
        /*%expand*/*
      from
        employee
      where
      /*%for age : ages */
        age = /* age */30
        /*%if age_has_next */
        /*# "or" */
        /*%end */
      /*%end */
      """)
  @Select
  List<Employee> selectByAges(List<Age> ages);

  @Sql(
      """
      select
        /*%expand*/*
      from
        employee
      where
      /*%if name != null*/
        name = /*name*/'hoge'
      /*%else */
        and
        name is null
      /*%end */
      """)
  @Select
  List<Employee> selectByName(String name);

  @Sql(
      """
      select
        /*%expand*/*
      from
        employee
      where
        name in /* names */('aaa', 'bbb')
      """)
  @Select
  List<Employee> selectByNames(List<String> names);

  @Sql(
      """
      select
        /*%expand*/*
      from
        employee
      where
        /*%if @isNotEmpty(name) */ name = /* name */'hoge' /*%end*/
      """)
  @Select
  List<Employee> selectByNotEmptyName(String name);

  @Sql(
      """
      select
        /*%expand*/*
      from
        employee
      where
        name like /* @prefix(prefix) */'X%' escape '$'
      """)
  @Select
  List<Employee> selectByNameWithPrefixMatching(String prefix);

  @Sql(
      """
      select
        /*%expand*/*
      from
        employee
      where
        name like /* @suffix(suffix) */'%X' escape '$'
      """)
  @Select
  List<Employee> selectByNameWithSuffixMatching(String suffix);

  @Sql(
      """
      select
        /*%expand*/*
      from
        employee
      where
        name like /* @infix(inside) */'%X%' escape '$'
      """)
  @Select
  List<Employee> selectByNameWithInfixMatching(String inside);

  @Sql(
      """
      select
        /*%expand*/*
      from
        employee
      where
        hiredate >= /* @roundDownTimePart(from) */'2001-01-01 12:34:56'
        and
        hiredate < /* @roundUpTimePart(to) */'2001-01-01 12:34:56'
      """)
  @Select
  List<Employee> selectByHiredateRange(Timestamp from, Timestamp to);

  @Sql(
      """
      select
        /*%expand*/*
      from
        employee
      where
        salary > /* salary */0
      """)
  @Select
  List<Employee> selectBySalary(Salary salary);

  @Sql(
      """
      select
        sum(salary)
      from
        employee
      """)
  @Select
  Salary selectSummedSalary();

  @Sql(
      """
      select
        /*%expand*/*
      from
        employee
      where
        name = /* e.name */'aaa'
      """)
  @Select
  List<Employee> selectByExample(Employee e);

  @Sql(
      """
      select
        /*%expand*/*
      from
        employee
      order by
        id
      """)
  @Select
  List<Employee> selectAll();

  @Sql(
      """
      select
        /*%expand*/*
      from
        employee
      order by
        id
      """)
  @Select
  List<Employee> selectAll(SelectOptions options);

  @Sql(
      """
      select
        /*%expand*/*
      from
        employee
      where
        age > /* age */0
      order by
        age
      """)
  @Select(strategy = SelectType.STREAM)
  <R> R selectByAge(int age, Function<Stream<Employee>, R> mapper);

  @Sql(
      """
      select
        /*%expand */*
      from
        employee e
      left outer join
        department d
      on
        e.department_id = d.id
      order by
        e.id
      """)
  @Select(aggregateStrategy = EmployeeAggregateStrategy.class)
  List<Employee> selectAllEmployeeDepartment();

  @Sql(
      """
      insert into Employee (
        ID,
        NAME,
        AGE,
        DEPARTMENT_ID,
        HIREDATE,
        JOB_TYPE,
        SALARY,
        INSERTTIMESTAMP,
        UPDATETIMESTAMP,
        VERSION
      ) values (
        /* employee.id */1,
        /* employee.name　*/'test',
        /* employee.age */10,
        /* employee.departmentId */1,
        /* employee.hiredate */'2010-01-01',
        /* employee.jobType */'SALESMAN',
        /* employee.salary */300,
        /* employee.insertTimestamp */'2010-01-01 12:34:56',
        /* employee.updateTimestamp */'2010-01-01 12:34:56',
        /* employee.version */1
      )
      """)
  @Insert
  int insert(Employee employee);

  @Sql(
      """
      update
        Employee
      set
        NAME = /* employee.name */'test',
        AGE = /* employee.age */10,
        DEPARTMENT_ID = /* employee.departmentId */1,
        HIREDATE = /* employee.hiredate */date'2010-01-01',
        JOB_TYPE = /* employee.jobType */'SALESMAN',
        SALARY = /* employee.salary */300,
        UPDATETIMESTAMP = /* employee.updateTimestamp */timestamp'2010-01-01 12:34:56',
        VERSION = /* employee.version */1
      where
        ID = /* employee.id */1
      """)
  @Update
  int update(Employee employee);

  @Sql(
      """
      delete
      from
        Employee
      where
        ID = /* employee.id */0
      """)
  @Delete
  int delete(Employee employee);
}

@AggregateStrategy(root = Employee.class, tableAlias = "e")
interface EmployeeAggregateStrategy {
  @AssociationLinker(propertyPath = "department", tableAlias = "d")
  BiFunction<Employee, Department, Employee> department =
      (e, d) -> {
        e.setDepartment(d);
        return e;
      };
}
