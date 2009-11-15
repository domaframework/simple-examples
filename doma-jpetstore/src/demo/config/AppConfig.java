package demo.config;

import javax.sql.DataSource;

import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.JdbcLogger;
import org.seasar.doma.jdbc.RequiresNewController;
import org.seasar.doma.jdbc.SqlFileRepository;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.HsqldbDialect;
import org.seasar.extension.datasource.DataSourceFactory;
import org.seasar.extension.datasource.impl.SelectableDataSourceProxy;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

public class AppConfig implements Config {

    private static SqlFileRepository sqlfileRepository = new DisposableSqlFileRepository();

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
        return getClass().getName();
    }

    @Override
    public DataSource getDataSource() {
        return getDataSourceInternal();
    }

    protected DataSource getDataSourceInternal() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (DataSource) container.getComponent(DataSource.class);
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

    @Override
    public SqlFileRepository getSqlFileRepository() {
        return sqlfileRepository;
    }

    @Override
    public int getBatchSize() {
        return 10;
    }

    @Override
    public int getFetchSize() {
        return 0;
    }

    @Override
    public int getMaxRows() {
        return 0;
    }

    @Override
    public int getQueryTimeout() {
        return 0;
    }

}
