package example.criteria_api.repository;

import example.criteria_api.domain.Age;
import example.criteria_api.domain.Salary;
import example.criteria_api.entity.Department_;
import example.criteria_api.entity.Employee;
import example.criteria_api.entity.Employee_;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.criteria.Entityql;
import org.seasar.doma.jdbc.criteria.NativeSql;
import org.seasar.doma.jdbc.criteria.expression.Expressions;
import org.seasar.doma.jdbc.criteria.option.LikeOption;
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
    var e = new Employee_();
    return entityql.from(e).where(c -> c.eq(e.id, id)).fetchOne();
  }

  public List<Employee> selectByAgeRange(Age min, Age max) {
    var e = new Employee_();
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
    var e = new Employee_();
    return entityql.from(e).where(c -> c.eq(e.name, name)).fetch();
  }

  public List<Employee> selectByNames(List<String> names) {
    var e = new Employee_();
    return entityql.from(e).where(c -> c.in(e.name, names)).fetch();
  }

  public List<Employee> selectByAges(List<Age> ages) {
    var e = new Employee_();
    return entityql.from(e).where(c -> c.in(e.age, ages)).fetch();
  }

  public List<Employee> selectByNotEmptyName(String name) {
    var e = new Employee_();
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
    var e = new Employee_();
    return entityql.from(e).where(c -> c.like(e.name, prefix, LikeOption.prefix())).fetch();
  }

  public List<Employee> selectByNameWithSuffixMatching(String suffix) {
    var e = new Employee_();
    return entityql.from(e).where(c -> c.like(e.name, suffix, LikeOption.suffix())).fetch();
  }

  public List<Employee> selectByNameWithInfixMatching(String inside) {
    var e = new Employee_();
    return entityql.from(e).where(c -> c.like(e.name, inside, LikeOption.infix())).fetch();
  }

  public List<Employee> selectByHiredateRange(LocalDateTime from, LocalDateTime to) {
    var fromDate = from.toLocalDate();
    var toDate = to.toLocalDate().plusDays(1);
    var e = new Employee_();
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
    var e = new Employee_();
    return entityql.from(e).where(c -> c.gt(e.salary, salary)).fetch();
  }

  public Optional<Salary> selectSummedSalary() {
    var e = new Employee_();
    return nativeSql.from(e).select(Expressions.sum(e.salary)).fetchOptional();
  }

  public List<Employee> selectByExample(Employee employee) {
    var e = new Employee_();
    return entityql.from(e).where(c -> c.eq(e.name, employee.getName())).fetch();
  }

  public List<Employee> selectAll() {
    return select(null, null);
  }

  public Tuple2<List<Employee>, Long> selectAndCount(Integer offset, Integer limit) {
    var list = select(offset, limit);
    var e = new Employee_();
    var count = nativeSql.from(e).select(Expressions.count()).fetchOptional().orElse(0L);
    return new Tuple2<>(list, count);
  }

  public List<Employee> select(Integer offset, Integer limit) {
    var e = new Employee_();
    return nativeSql.from(e).orderBy(c -> c.asc(e.id)).offset(offset).limit(limit).fetch();
  }

  public <R> R selectByAge(Age age, Function<Stream<Employee>, R> mapper) {
    var e = new Employee_();
    var stmt = nativeSql.from(e).where(c -> c.gt(e.age, age)).orderBy(c -> c.asc(e.age));
    return stmt.mapStream(mapper);
  }

  public long selectCount() {
    var e = new Employee_();
    return nativeSql.from(e).select(Expressions.count()).fetchOptional().orElse(0L);
  }

  public List<Employee> selectAllWithAssociation() {
    var e = new Employee_();
    var d = new Department_();
    return entityql
        .from(e)
        .leftJoin(d, on -> on.eq(e.departmentId, d.id))
        .orderBy(c -> c.asc(e.id))
        .associate(e, d, Employee::setDepartment)
        .fetch();
  }

  public void insert(Employee employee) {
    var e = new Employee_();
    entityql.insert(e, employee).execute();
  }

  public int insertByNativeSql(Employee employee) {
    var e = new Employee_();
    return nativeSql
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
    var e = new Employee_();
    entityql.update(e, employee).execute();
  }

  public int updateByNativeSql(Employee employee) {
    var e = new Employee_();
    return nativeSql
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
    var e = new Employee_();
    entityql.delete(e, employee).execute();
  }

  public int deleteByNativeSql(Employee employee) {
    var e = new Employee_();
    return nativeSql.delete(e).where(c -> c.eq(e.id, employee.getId())).execute();
  }

  public void batchInsert(List<Employee> employees) {
    var e = new Employee_();
    var stmt = entityql.insert(e, employees);
    stmt.execute();
  }

  public void batchUpdate(List<Employee> employees) {
    var e = new Employee_();
    entityql.update(e, employees).execute();
  }

  public void batchDelete(List<Employee> employees) {
    var e = new Employee_();
    entityql.delete(e, employees).execute();
  }
}
