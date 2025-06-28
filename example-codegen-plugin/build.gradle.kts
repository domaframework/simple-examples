plugins {
    java
    alias(libs.plugins.doma.codegen) // Doma code generation plugin
}

// Dependency configuration
dependencies {
    // Test runtime dependencies
    testImplementation(platform(libs.testcontainers.bom)) // Testcontainers dependency management
    testImplementation(libs.jdbc.mysql) // MySQL JDBC driver
    testImplementation(libs.jdbc.postgresql) // PostgreSQL JDBC driver
    testImplementation(libs.testcontainers.mysql) // MySQL Testcontainers
    testImplementation(libs.testcontainers.postgresql) // PostgreSQL Testcontainers

    // Doma code generation dependencies
    domaCodeGen(platform(libs.testcontainers.bom)) // Testcontainers dependency management for code generation
    domaCodeGen(libs.jdbc.mysql) // MySQL JDBC driver for code generation
    domaCodeGen(libs.jdbc.postgresql) // PostgreSQL JDBC driver for code generation
    domaCodeGen(libs.testcontainers.mysql) // MySQL Testcontainers for code generation
    domaCodeGen(libs.testcontainers.postgresql) // PostgreSQL Testcontainers for code generation
}

// Doma code generation configuration
domaCodeGen {
    // Base package name for generated code
    val basePackage = "example.generated"

    // MySQL configuration
    register("mysql") {
        // Path to initialization script
        val initScript = file("src/main/resources/init_mysql.sql")
        // MySQL connection URL using Testcontainers with initialization script
        url.set("jdbc:tc:mysql:8.4.5:///test?TC_INITSCRIPT=file:${initScript.absolutePath}")
        user.set("test") // Database username
        password.set("test") // Database password
        // Entity class generation settings
        entity {
            packageName.set("$basePackage.mysql.entity") // Package name for entity classes
        }
        // DAO class generation settings
        dao {
            packageName.set("$basePackage.mysql.dao") // Package name for DAO classes
        }
    }
    // PostgreSQL configuration
    register("postgresql") {
        // Path to initialization script
        val initScript = file("src/main/resources/init_postgresql.sql")
        // PostgreSQL connection URL using Testcontainers with initialization script
        url.set("jdbc:tc:postgresql:13.21:///test?TC_INITSCRIPT=file:${initScript.absolutePath}")
        user.set("test") // Database username
        password.set("test") // Database password
        // Entity class generation settings
        entity {
            packageName.set("$basePackage.postgresql.entity") // Package name for entity classes
        }
        // DAO class generation settings
        dao {
            packageName.set("$basePackage.postgresql.dao") // Package name for DAO classes
        }
    }
}

tasks {
    // Clean task to remove generated source files
    val cleanGeneratedSources by register<Delete>("cleanGeneratedSources") {
        description = "Deletes generated source files by Doma code generation"
        delete(
            fileTree("src/main/java") {
                include("example/generated/**")
            }
        )
        delete(
            fileTree("src/test/java") {
                include("example/generated/**")
            }
        )
        delete(
            fileTree("src/main/resources/META-INF") {
                include("example/generated/**")
            }
        )
    }

    // Configure the main clean task to also clean generated sources
    clean {
        dependsOn(cleanGeneratedSources)
    }

    // Configure test task to generate all source files before running tests
    test {
        dependsOn("domaCodeGenMysqlAll", "domaCodeGenPostgresqlAll")
    }
}
