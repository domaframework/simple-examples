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
package example.dao;

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

import example.AppConfig;
import example.domain.Identity;
import example.domain.Name;
import example.entity.Employee;

@Dao(config = AppConfig.class)
public interface EmployeeDao {

	@Select
	Employee selectById(Identity id);

	@Select
	List<Employee> selectAll();

	@Select
	List<Employee> selectByNames(List<Name> names);

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
