package demo.config;

import javax.sql.DataSource;

import org.seasar.doma.jdbc.DomaAbstractConfig;
import org.seasar.doma.jdbc.JdbcLogger;
import org.seasar.doma.jdbc.RequiresNewController;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.HsqldbDialect;
import org.seasar.extension.datasource.DataSourceFactory;
import org.seasar.extension.datasource.impl.SelectableDataSourceProxy;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

public class AppConfig extends DomaAbstractConfig {

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

}
