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
package demo.config;

import java.sql.Connection;

import javax.servlet.ServletException;
import javax.sql.DataSource;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import com.ibatis.common.jdbc.ScriptRunner;
import com.ibatis.common.resources.Resources;

public class HsqldbInitializer {

    public static void init() throws ServletException {
        try {
            S2Container container = SingletonS2ContainerFactory.getContainer();
            DataSource dataSource = (DataSource) container
                    .getComponent(DataSource.class);
            Connection conn = dataSource.getConnection();
            try {
                ScriptRunner runner = new ScriptRunner(conn, false, false);
                runner.setErrorLogWriter(null);
                runner.setLogWriter(null);
                runner
                        .runScript(Resources
                                .getResourceAsReader("META-INF/hsqldb/jpetstore-hsqldb-schema.sql"));
                runner
                        .runScript(Resources
                                .getResourceAsReader("META-INF/hsqldb//jpetstore-hsqldb-dataload.sql"));
            } finally {
                conn.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
