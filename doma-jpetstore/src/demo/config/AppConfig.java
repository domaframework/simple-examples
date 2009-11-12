package demo.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import javax.sql.DataSource;

import org.seasar.doma.jdbc.DomaAbstractConfig;
import org.seasar.doma.jdbc.JdbcLogger;
import org.seasar.doma.jdbc.RequiresNewController;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.HsqldbDialect;
import org.seasar.extension.datasource.DataSourceFactory;
import org.seasar.extension.datasource.impl.SelectableDataSourceProxy;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.SingletonS2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import com.ibatis.common.jdbc.ScriptRunner;
import com.ibatis.common.resources.Resources;

public class AppConfig extends DomaAbstractConfig {

    static {
        try {
            Properties props = Resources
                    .getResourceAsProperties("properties/database.properties");
            String url = props.getProperty("url");
            String driver = props.getProperty("driver");
            String username = props.getProperty("username");
            String password = props.getProperty("password");
            if (url.equals("jdbc:hsqldb:mem:jpetstore")) {
                Class.forName(driver).newInstance();
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
            throw new RuntimeException(e);
        }
    }

    private static JdbcLogger jdbcLogger = new CommonsLogger();

    private static RequiresNewController requiresNewController = new S2RequiresNewController();

    private static Dialect dialect = new HsqldbDialect();

    @Override
    public String getDataSourceName() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        if (container.hasComponentDef(DataSourceFactory.class)) {
            DataSourceFactory dataSourceFactory = (DataSourceFactory) container
                    .getComponent(DataSourceFactory.class);
            if (getDataSourceInternal() instanceof SelectableDataSourceProxy) {
                return dataSourceFactory.getSelectableDataSourceName();
            }
        }
        return super.getDataSourceName();
    }

    @Override
    public DataSource getDataSource() {
        return getDataSourceInternal();
    }

    protected DataSource getDataSourceInternal() {
        return SingletonS2Container.getComponent(DataSource.class);
    }

    @Override
    public Dialect getDialect() {
        return dialect;
    }

    @Override
    public RequiresNewController getRequiresNewController() {
        return requiresNewController;
    }

    @Override
    public JdbcLogger getJdbcLogger() {
        return jdbcLogger;
    }

}
