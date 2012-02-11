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
package tutorial;

import javax.sql.DataSource;

import org.seasar.doma.jdbc.DomaAbstractConfig;
import org.seasar.doma.jdbc.SimpleDataSource;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.H2Dialect;
import org.seasar.doma.jdbc.tx.KeepAliveLocalTransaction;
import org.seasar.doma.jdbc.tx.LocalTransaction;
import org.seasar.doma.jdbc.tx.LocalTransactionalDataSource;

public class AppConfig extends DomaAbstractConfig {

    private static final DataSource originalDataSource = createDataSource();

    private static final LocalTransactionalDataSource localTxDataSource = createLocalTxDataSource();

    private static final Dialect dialect = new H2Dialect();

    @Override
    public DataSource getDataSource() {
        return localTxDataSource;
    }

    @Override
    public Dialect getDialect() {
        return dialect;
    }

    protected static DataSource createDataSource() {
        SimpleDataSource dataSource = new SimpleDataSource();
        dataSource.setUrl("jdbc:h2:mem:tutorial;DB_CLOSE_DELAY=-1");
        dataSource.setUser("sa");
        return dataSource;
    }

    protected static LocalTransactionalDataSource createLocalTxDataSource() {
        return new LocalTransactionalDataSource(originalDataSource);
    }

    public static LocalTransaction getLocalTransaction() {
        return localTxDataSource.getLocalTransaction(defaultJdbcLogger);
    }

    public static KeepAliveLocalTransaction getKeepAliveLocalTransaction() {
        return localTxDataSource
                .getKeepAliveLocalTransaction(defaultJdbcLogger);
    }

    public static DataSource getOriginalDataSource() {
        return originalDataSource;
    }
}
