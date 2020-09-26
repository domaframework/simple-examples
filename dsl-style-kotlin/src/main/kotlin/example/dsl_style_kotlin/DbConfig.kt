package example.dsl_style_kotlin

import org.seasar.doma.jdbc.Config
import org.seasar.doma.jdbc.JdbcLogger
import org.seasar.doma.jdbc.dialect.Dialect
import org.seasar.doma.jdbc.tx.TransactionManager
import javax.sql.DataSource

class DbConfig(
    private val dialect: Dialect,
    private val dataSource: DataSource,
    private val jdbcLogger: JdbcLogger,
    private val transactionManager: TransactionManager
) : Config {
    override fun getJdbcLogger(): JdbcLogger {
        return jdbcLogger
    }

    override fun getDialect(): Dialect {
        return dialect
    }

    override fun getDataSource(): DataSource {
        return dataSource
    }

    override fun getTransactionManager(): TransactionManager {
        return transactionManager
    }
}
