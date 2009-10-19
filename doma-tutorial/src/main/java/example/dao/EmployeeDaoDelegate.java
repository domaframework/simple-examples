package example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.seasar.doma.jdbc.Config;

public class EmployeeDaoDelegate {

    protected final Config config;

    public EmployeeDaoDelegate(Config config) {
        if (config == null) {
            throw new NullPointerException("config");
        }
        this.config = config;
    }

    public int count() {
        try {
            Connection connection = config.dataSource().getConnection();
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
