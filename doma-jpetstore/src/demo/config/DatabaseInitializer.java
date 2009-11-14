package demo.config;

import java.sql.Connection;

import javax.servlet.ServletException;
import javax.sql.DataSource;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import com.ibatis.common.jdbc.ScriptRunner;
import com.ibatis.common.resources.Resources;

public class DatabaseInitializer {

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
