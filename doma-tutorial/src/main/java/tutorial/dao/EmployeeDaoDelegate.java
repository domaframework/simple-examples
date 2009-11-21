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
