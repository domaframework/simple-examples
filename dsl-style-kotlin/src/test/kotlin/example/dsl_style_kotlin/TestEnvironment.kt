package example.dsl_style_kotlin

import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.AfterTestExecutionCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver
import org.seasar.doma.jdbc.dialect.H2Dialect
import org.seasar.doma.jdbc.tx.LocalTransactionDataSource
import org.seasar.doma.jdbc.tx.LocalTransactionManager
import org.seasar.doma.slf4j.Slf4jJdbcLogger

class TestEnvironment : BeforeAllCallback, AfterAllCallback, BeforeTestExecutionCallback, AfterTestExecutionCallback, ParameterResolver {

    private val dialect = H2Dialect()
    private val dataSource = LocalTransactionDataSource("jdbc:h2:mem:tutorial;DB_CLOSE_DELAY=-1", "sa", null)
    private val jdbcLogger = Slf4jJdbcLogger()
    private val transactionManager = LocalTransactionManager(dataSource, jdbcLogger)
    private val config = DbConfig(dialect, dataSource, jdbcLogger, transactionManager)
    private val dao = example.dsl_style_kotlin.dao.ScriptDaoImpl(config)

    override fun beforeAll(context: ExtensionContext) {
        transactionManager.required { dao.create() }
    }

    override fun afterAll(context: ExtensionContext) {
        transactionManager.required { dao.drop() }
    }

    override fun beforeTestExecution(context: ExtensionContext) {
        transactionManager.transaction.begin()
    }

    override fun afterTestExecution(context: ExtensionContext) {
        transactionManager.transaction.rollback()
    }

    override fun supportsParameter(
        parameterContext: ParameterContext,
        extensionContext: ExtensionContext
    ): Boolean {
        return parameterContext.parameter.type.isAssignableFrom(DbConfig::class.java)
    }

    override fun resolveParameter(
        parameterContext: ParameterContext,
        extensionContext: ExtensionContext
    ): Any {
        return config
    }
}
