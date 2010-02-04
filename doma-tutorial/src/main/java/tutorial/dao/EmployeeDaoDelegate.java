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
package tutorial.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.seasar.doma.jdbc.Config;

public class EmployeeDaoDelegate {

    protected final Config config;

    protected final EmployeeDao dao;

    public EmployeeDaoDelegate(Config config, EmployeeDao dao) {
        if (config == null) {
            throw new NullPointerException("config");
        }
        if (dao == null) {
            throw new NullPointerException("dao");
        }
        this.config = config;
        this.dao = dao;
    }

    public int count() {
        try {
            Connection connection = config.getDataSource().getConnection();
            try {
                PreparedStatement preparedStatement = connection
                        .prepareStatement("select count(*) from employee");
                try {
                    ResultSet resultSet = preparedStatement.executeQuery();
                    try {
                        if (resultSet.next()) {
                            return resultSet.getInt(1);
                        }
                        return 0;
                    } finally {
                        resultSet.close();
                    }
                } finally {
                    preparedStatement.close();
                }
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
