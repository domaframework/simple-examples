/*
 * Copyright 2004-2010 the Seasar Foundation and the Others.
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
package tutorial.entity;

import java.sql.Timestamp;

import org.seasar.doma.jdbc.entity.EntityListener;
import org.seasar.doma.jdbc.entity.PostDeleteContext;
import org.seasar.doma.jdbc.entity.PostInsertContext;
import org.seasar.doma.jdbc.entity.PostUpdateContext;
import org.seasar.doma.jdbc.entity.PreDeleteContext;
import org.seasar.doma.jdbc.entity.PreInsertContext;
import org.seasar.doma.jdbc.entity.PreUpdateContext;

public class EmployeeListener implements EntityListener<Employee> {

    @Override
    public void preDelete(Employee employee, PreDeleteContext context) {
    }

    @Override
    public void preInsert(Employee employee, PreInsertContext context) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        employee.setInsertTimestamp(timestamp);
    }

    @Override
    public void preUpdate(Employee employee, PreUpdateContext context) {
        if (context.isEntityChanged()) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            employee.setUpdateTimestamp(timestamp);
        }
    }

    @Override
    public void postInsert(Employee entity, PostInsertContext context) {
    }

    @Override
    public void postUpdate(Employee entity, PostUpdateContext context) {
    }

    @Override
    public void postDelete(Employee entity, PostDeleteContext context) {
    }

}
