/*
 * Copyright 2004-2009 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package tutorial.dao;

import java.sql.Timestamp;
import java.util.List;

import org.seasar.doma.BatchDelete;
import org.seasar.doma.BatchInsert;
import org.seasar.doma.BatchUpdate;
import org.seasar.doma.Dao;
import org.seasar.doma.Delegate;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.jdbc.IterationCallback;
import org.seasar.doma.jdbc.SelectOptions;

import tutorial.AppConfig;
import tutorial.domain.Salary;
import tutorial.entity.Employee;
import tutorial.entity.JobType;

@Dao(config = AppConfig.class)
public interface EmployeeDao {

    @Select
    Employee selectById(Integer id);

    @Select
    List<Employee> selectByAgeRange(Integer min, Integer max);

    @Select
    List<Employee> selectByName(String name);

    @Select
    List<Employee> selectByNames(List<String> names);

    @Select
    List<Employee> selectByNamePrefix(String prefix);

    @Select
    List<Employee> selectByNameSuffix(String suffix);

    @Select
    List<Employee> selectByFuzzyName(String fuzzyName);

    @Select
    List<Employee> selectByHiredateRange(Timestamp from, Timestamp to);

    @Select
    List<Employee> selectByJobType(JobType jobType);

    @Select
    List<JobType> selectAllJobTypes();

    @Select
    List<Employee> selectBySalary(Salary salary);

    @Select
    Salary selectSummedSalary();

    @Select
    List<Employee> selectByExample(Employee e);

    @Select
    List<Employee> selectAll();

    @Select
    List<Employee> selectAll(SelectOptions options);

    @Select(iterate = true)
    <R> R selectAll(IterationCallback<R, Employee> callback);

    @Delegate(to = EmployeeDaoDelegate.class)
    int count();

    @Insert
    int insert(Employee employee);

    @Update
    int update(Employee employee);

    @Delete
    int delete(Employee employee);

    @BatchInsert
    int[] batchInsert(List<Employee> employees);

    @BatchUpdate
    int[] batchUpdate(List<Employee> employees);

    @BatchDelete
    int[] batchDelete(List<Employee> employees);

}
