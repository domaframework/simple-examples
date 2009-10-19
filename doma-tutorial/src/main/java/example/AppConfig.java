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
package example;

import javax.sql.DataSource;

import org.seasar.doma.jdbc.DomaAbstractConfig;
import org.seasar.doma.jdbc.SimpleDataSource;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.HsqldbDialect;

public class AppConfig extends DomaAbstractConfig {

    protected static final DataSource dataSource = createDataSource();

    protected static final Dialect dialect = new HsqldbDialect();

    @Override
    public DataSource dataSource() {
        return dataSource;
    }

    @Override
    public Dialect dialect() {
        return dialect;
    }

    protected static DataSource createDataSource() {
        SimpleDataSource dataSource = new SimpleDataSource();
        dataSource.setUrl("jdbc:hsqldb:mem:tutorial");
        dataSource.setUser("sa");
        return dataSource;
    }

}
