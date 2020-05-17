package examples.dao;

import static org.seasar.doma.jdbc.criteria.expression.Expressions.count;
import static org.seasar.doma.jdbc.criteria.expression.Expressions.sum;

import examples.domain.Age;
import examples.domain.Salary;
import examples.entity.Department_;
import examples.entity.Employee;
import examples.entity.Employee_;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;
import org.seasar.doma.Dao;
import org.seasar.doma.Script;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.criteria.Entityql;
import org.seasar.doma.jdbc.criteria.NativeSql;
import org.seasar.doma.jdbc.criteria.option.LikeOption;
import org.seasar.doma.jdbc.criteria.tuple.Tuple2;

@Dao
public interface EmployeeDao {

  private Entityql entityql() {
    return new Entityql(Config.get(this));
  }

  private NativeSql nativeSql() {
    return new NativeSql(Config.get(this));
  }

  default Optional<Employee> selectById(Integer id) {
    var e = new Employee_();
    return entityql().from(e).where(c -> c.eq(e.id, id)).fetchOptional();
  }

  default List<Employee> selectByAgeRange(Age min, Age max) {
    var e = new Employee_();
    return entityql()
        .from(e)
        .where(
            c -> {
              if (min != null) {
                c.ge(e.age, min);
              }
              if (max != null) {
                c.le(e.age, max);
              }
            })
        .fetch();
  }

  default List<Employee> selectByName(String name) {
    var e = new Employee_();
    return entityql().from(e).where(c -> c.eq(e.name, name)).fetch();
  }

  default List<Employee> selectByNames(List<String> names) {
    var e = new Employee_();
    return entityql().from(e).where(c -> c.in(e.name, names)).fetch();
  }

  default List<Employee> selectByAges(List<Age> ages) {
    var e = new Employee_();
    return entityql().from(e).where(c -> c.in(e.age, ages)).fetch();
  }

  default List<Employee> selectByNotEmptyName(String name) {
    var e = new Employee_();
    return entityql()
        .from(e)
        .where(
            c -> {
              if (name != null && !name.isEmpty()) {
                c.eq(e.name, name);
              }
            })
        .fetch();
  }

  default List<Employee> selectByNameWithPrefixMatching(String prefix) {
    var e = new Employee_();
    return entityql().from(e).where(c -> c.like(e.name, prefix, LikeOption.prefix())).fetch();
  }

  default List<Employee> selectByNameWithSuffixMatching(String suffix) {
    var e = new Employee_();
    return entityql().from(e).where(c -> c.like(e.name, suffix, LikeOption.suffix())).fetch();
  }

  default List<Employee> selectByNameWithInfixMatching(String inside) {
    var e = new Employee_();
    return entityql().from(e).where(c -> c.like(e.name, inside, LikeOption.infix())).fetch();
  }

  default List<Employee> selectByHiredateRange(LocalDateTime from, LocalDateTime to) {
    var fromDate = from.toLocalDate();
    var toDate = to.toLocalDate().plusDays(1);
    var e = new Employee_();
    return entityql()
        .from(e)
        .where(
            c -> {
              c.ge(e.hiredate, fromDate);
              c.lt(e.hiredate, toDate);
            })
        .fetch();
  }

  default List<Employee> selectBySalary(Salary salary) {
    var e = new Employee_();
    return entityql().from(e).where(c -> c.gt(e.salary, salary)).fetch();
  }

  default Optional<Salary> selectSummedSalary() {
    var e = new Employee_();
    return nativeSql().from(e).select(sum(e.salary)).fetchOptional();
  }

  default List<Employee> selectByExample(Employee employee) {
    var e = new Employee_();
    return entityql().from(e).where(c -> c.eq(e.name, employee.getName())).fetch();
  }

  default List<Employee> selectAll() {
    return select(null, null);
  }

  default Tuple2<List<Employee>, Long> selectAndCount(Integer offset, Integer limit) {
    var list = select(offset, limit);
    var e = new Employee_();
    var count = nativeSql().from(e).select(count()).fetchOptional().orElse(0L);
    return new Tuple2<>(list, count);
  }

  default List<Employee> select(Integer offset, Integer limit) {
    var e = new Employee_();
    return nativeSql().from(e).orderBy(c -> c.asc(e.id)).offset(offset).limit(limit).fetch();
  }

  default <R> R selectByAge(Age age, Function<Stream<Employee>, R> mapper) {
    var e = new Employee_();
    var stmt = nativeSql().from(e).where(c -> c.gt(e.age, age)).orderBy(c -> c.asc(e.age));
    return stmt.mapStream(mapper);
  }

  default long selectCount() {
    var e = new Employee_();
    return nativeSql().from(e).select(count()).fetchOptional().orElse(0L);
  }

  default List<Employee> selectAllWithAssociation() {
    var e = new Employee_();
    var d = new Department_();
    return entityql()
        .from(e)
        .leftJoin(d, on -> on.eq(e.departmentId, d.id))
        .orderBy(c -> c.asc(e.id))
        .associate(e, d, Employee::setDepartment)
        .fetch();
  }

  default void insert(Employee employee) {
    var e = new Employee_();
    entityql().insert(e, employee).execute();
  }

  default int insertByNativeSql(Employee employee) {
    var e = new Employee_();
    return nativeSql()
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

  default void update(Employee employee) {
    var e = new Employee_();
    entityql().update(e, employee).execute();
  }

  default int updateByNativeSql(Employee employee) {
    var e = new Employee_();
    return nativeSql()
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

  default void delete(Employee employee) {
    var e = new Employee_();
    entityql().delete(e, employee).execute();
  }

  default int deleteByNativeSql(Employee employee) {
    var e = new Employee_();
    return nativeSql().delete(e).where(c -> c.eq(e.id, employee.getId())).execute();
  }

  default void batchInsert(List<Employee> employees) {
    var e = new Employee_();
    var stmt = entityql().insert(e, employees);
    stmt.execute();
  }

  default void batchUpdate(List<Employee> employees) {
    var e = new Employee_();
    entityql().update(e, employees).execute();
  }

  default void batchDelete(List<Employee> employees) {
    var e = new Employee_();
    entityql().delete(e, employees).execute();
  }

  @Script
  void create();

  @Script
  void drop();
}
