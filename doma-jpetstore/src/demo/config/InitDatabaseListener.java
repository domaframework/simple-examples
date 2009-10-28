package demo.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.ibatis.common.jdbc.ScriptRunner;
import com.ibatis.common.resources.Resources;

public class InitDatabaseListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Properties props = Resources
                    .getResourceAsProperties("properties/database.properties");
            String url = props.getProperty("url");
            String driver = props.getProperty("driver");
            String username = props.getProperty("username");
            String password = props.getProperty("password");
            if (url.equals("jdbc:hsqldb:mem:jpetstore")) {
                Connection conn = DriverManager.getConnection(url, username,
                        password);
                try {
                    ScriptRunner runner = new ScriptRunner(conn, false, false);
                    runner.setErrorLogWriter(null);
                    runner.setLogWriter(null);
                    runner
                            .runScript(Resources
                                    .getResourceAsReader("ddl/hsql/jpetstore-hsqldb-schema.sql"));
                    runner
                            .runScript(Resources
                                    .getResourceAsReader("ddl/hsql/jpetstore-hsqldb-dataload.sql"));
                } finally {
                    conn.close();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Description.  Cause: " + e, e);
        }
    }
}
