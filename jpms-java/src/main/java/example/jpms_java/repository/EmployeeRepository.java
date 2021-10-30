package example.jpms_java.repository;

import example.jpms_java.domain.Age;
import example.jpms_java.domain.Salary;
import example.jpms_java.entity.Department_;
import example.jpms_java.entity.Employee;
import example.jpms_java.entity.Employee_;
import example.jpms_java.entity.NameAndSalaryDto;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.criteria.Entityql;
import org.seasar.doma.jdbc.criteria.NativeSql;
import org.seasar.doma.jdbc.criteria.expression.Expressions;
import org.seasar.doma.jdbc.criteria.option.LikeOption;
import org.seasar.doma.jdbc.criteria.statement.StreamMappable;
import org.seasar.doma.jdbc.criteria.tuple.Tuple2;

public class EmployeeRepository {

  private final Entityql entityql;

  private final NativeSql nativeSql;

  public EmployeeRepository(Config config) {
    Objects.requireNonNull(config);
    entityql = new Entityql(config);
    nativeSql = new NativeSql(config);
  }

  public Employee selectById(Integer id) {
    Employee_ e = new Employee_();
    return entityql.from(e).where(c -> c.eq(e.id, id)).fetchOne();
  }

  public List<Employee> selectByAgeRange(Age min, Age max) {
    Employee_ e = new Employee_();
    return entityql
        .from(e)
        .where(
            c -> {
              c.ge(e.age, min);
              c.le(e.age, max);
            })
        .fetch();
  }

  public List<Employee> selectByName(String name) {
    Employee_ e = new Employee_();
    return entityql.from(e).where(c -> c.eq(e.name, name)).fetch();
  }

  public List<Employee> selectByNames(List<String> names) {
    Employee_ e = new Employee_();
    return entityql.from(e).where(c -> c.in(e.name, names)).fetch();
  }

  public List<Employee> selectByAges(List<Age> ages) {
    Employee_ e = new Employee_();
    return entityql.from(e).where(c -> c.in(e.age, ages)).fetch();
  }

  public List<Employee> selectByNotEmptyName(String name) {
    Employee_ e = new Employee_();
    return entityql
        .from(e)
        .where(
            c -> {
              if (name != null && !name.isEmpty()) {
                c.eq(e.name, name);
              }
            })
        .fetch();
  }

  public List<Employee> selectByNameWithPrefixMatching(String prefix) {
    Employee_ e = new Employee_();
    return entityql.from(e).where(c -> c.like(e.name, prefix, LikeOption.prefix())).fetch();
  }

  public List<Employee> selectByNameWithSuffixMatching(String suffix) {
    Employee_ e = new Employee_();
    return entityql.from(e).where(c -> c.like(e.name, suffix, LikeOption.suffix())).fetch();
  }

  public List<Employee> selectByNameWithInfixMatching(String inside) {
    Employee_ e = new Employee_();
    return entityql.from(e).where(c -> c.like(e.name, inside, LikeOption.infix())).fetch();
  }

  public List<Employee> selectByHiredateRange(LocalDateTime from, LocalDateTime to) {
    LocalDate fromDate = from.toLocalDate();
    LocalDate toDate = to.toLocalDate().plusDays(1);
    Employee_ e = new Employee_();
    return entityql
        .from(e)
        .where(
            c -> {
              c.ge(e.hiredate, fromDate);
              c.lt(e.hiredate, toDate);
            })
        .fetch();
  }

  public List<Employee> selectBySalary(Salary salary) {
    Employee_ e = new Employee_();
    return entityql.from(e).where(c -> c.gt(e.salary, salary)).fetch();
  }

  public Optional<Salary> selectSummedSalary() {
    Employee_ e = new Employee_();
    return nativeSql.from(e).select(Expressions.sum(e.salary)).fetchOptional();
  }

  public List<Employee> selectByExample(Employee employee) {
    Employee_ e = new Employee_();
    return entityql.from(e).where(c -> c.eq(e.name, employee.getName())).fetch();
  }

  public List<Employee> selectAll() {
    return select(null, null);
  }

  public Tuple2<List<Employee>, Long> selectAndCount(Integer offset, Integer limit) {
    List<Employee> list = select(offset, limit);
    Employee_ e = new Employee_();
    long count = nativeSql.from(e).select(Expressions.count()).fetchOptional().orElse(0L);
    return new Tuple2<>(list, count);
  }

  public List<Employee> select(Integer offset, Integer limit) {
    Employee_ e = new Employee_();
    return nativeSql.from(e).orderBy(c -> c.asc(e.id)).offset(offset).limit(limit).fetch();
  }

  public <R> R selectByAge(Age age, Function<Stream<Employee>, R> mapper) {
    Employee_ e = new Employee_();
    StreamMappable<Employee> stmt =
        nativeSql.from(e).where(c -> c.gt(e.age, age)).orderBy(c -> c.asc(e.age));
    return stmt.mapStream(mapper);
  }

  public long selectCount() {
    Employee_ e = new Employee_();
    return nativeSql.from(e).select(Expressions.count()).fetchOptional().orElse(0L);
  }

  public List<Employee> selectAllWithAssociation() {
    Employee_ e = new Employee_();
    Department_ d = new Department_();
    return entityql
        .from(e)
        .leftJoin(d, on -> on.eq(e.departmentId, d.id))
        .orderBy(c -> c.asc(e.id))
        .associate(
            e,
            d,
            (employee, department) -> {
              employee.setDepartment(department);
              department.getEmployees().add(employee);
            })
        .fetch();
  }

  public List<Employee> selectByDepartmentName_in(String departmentName) {
    Employee_ e = new Employee_();
    Department_ d = new Department_();
    return entityql
        .from(e)
        .where(
            c ->
                c.in(
                    e.departmentId,
                    c.from(d).where(c2 -> c2.eq(d.name, departmentName)).select(d.id)))
        .fetch();
  }

  public List<Employee> selectByDepartmentName_exists(String departmentName) {
    Employee_ e = new Employee_();
    Department_ d = new Department_();
    return entityql
        .from(e)
        .where(
            c ->
                c.exists(
                    c.from(d)
                        .where(
                            c2 -> {
                              c2.eq(e.departmentId, d.id);
                              c2.eq(d.name, departmentName);
                            })))
        .fetch();
  }

  public List<Employee> selectByDepartmentName_join(String departmentName) {
    Employee_ e = new Employee_();
    Department_ d = new Department_();
    return entityql
        .from(e)
        .innerJoin(d, on -> on.eq(e.departmentId, d.id))
        .where(c -> c.eq(d.name, departmentName))
        .fetch();
  }

  public List<Employee> selectNameAndSalaryAsEntityList() {
    Employee_ e = new Employee_();
    return entityql.from(e).selectTo(e, e.name, e.salary).fetch();
  }

  public List<Tuple2<String, Salary>> selectNameAndSalaryAsTuple2List() {
    Employee_ e = new Employee_();
    return nativeSql.from(e).select(e.name, e.salary).fetch();
  }

  public List<NameAndSalaryDto> selectNameAndSalaryAsNameAndSalaryDtoList() {
    Employee_ e = new Employee_();
    return nativeSql.from(e).select(e.name, e.salary).stream()
        .map(tuple -> new NameAndSalaryDto(tuple.getItem1(), tuple.getItem2()))
        .collect(Collectors.toList());
  }

  public void insert(Employee employee) {
    Employee_ e = new Employee_();
    entityql.insert(e, employee).execute();
  }

  public void insertByNativeSql(Employee employee) {
    Employee_ e = new Employee_();
    nativeSql
        .insert(e)
        .values(
            c -> {
              c.value(e.id, employee.getId());
              c.value(e.name, employee.getName());
              c.value(e.age, employee.getAge());
              c.value(e.departmentId, employee.getDepartmentId());
              c.value(e.hiredate, employee.getHiredate());
              c.value(e.jobType, employee.getJobType());
              c.value(e.salary, employee.getSalary());
              c.value(e.insertTimestamp, employee.getInsertTimestamp());
              c.value(e.updateTimestamp, employee.getUpdateTimestamp());
              c.value(e.version, employee.getVersion());
            })
        .execute();
  }

  public void update(Employee employee) {
    Employee_ e = new Employee_();
    entityql.update(e, employee).execute();
  }

  public void updateByNativeSql(Employee employee) {
    Employee_ e = new Employee_();
    nativeSql
        .update(e)
        .set(
            c -> {
              c.value(e.name, employee.getName());
              c.value(e.age, employee.getAge());
              c.value(e.departmentId, employee.getDepartmentId());
              c.value(e.hiredate, employee.getHiredate());
              c.value(e.jobType, employee.getJobType());
              c.value(e.salary, employee.getSalary());
              c.value(e.updateTimestamp, employee.getUpdateTimestamp());
              c.value(e.version, employee.getVersion());
            })
        .where(c -> c.eq(e.id, employee.getId()))
        .execute();
  }

  public void delete(Employee employee) {
    Employee_ e = new Employee_();
    entityql.delete(e, employee).execute();
  }

  public void deleteByNativeSql(Employee employee) {
    Employee_ e = new Employee_();
    nativeSql.delete(e).where(c -> c.eq(e.id, employee.getId())).execute();
  }

  public void batchInsert(List<Employee> employees) {
    Employee_ e = new Employee_();
    entityql.insert(e, employees).execute();
  }

  public void batchUpdate(List<Employee> employees) {
    Employee_ e = new Employee_();
    entityql.update(e, employees).execute();
  }

  public void batchDelete(List<Employee> employees) {
    Employee_ e = new Employee_();
    entityql.delete(e, employees).execute();
  }
}
