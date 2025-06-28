# example-codegen-plugin

A sample project demonstrating code generation using the Doma Codegen Plugin.

## Overview

This project demonstrates how to use the [Doma Codegen Plugin](https://doma.readthedocs.io/en/stable/codegen/) to automatically generate entity classes and DAO classes from database schemas.

It supports both MySQL and PostgreSQL databases and uses Testcontainers to automatically start databases during code generation.

## Project Structure

```
example-codegen-plugin/
├── build.gradle.kts           # Build configuration file
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── example/
│   │   │       └── generated/  # Auto-generated code
│   │   │           ├── mysql/
│   │   │           │   ├── dao/      # MySQL DAO classes
│   │   │           │   └── entity/   # MySQL entity classes
│   │   │           └── postgresql/
│   │   │               ├── dao/      # PostgreSQL DAO classes
│   │   │               └── entity/   # PostgreSQL entity classes
│   │   └── resources/
│   │       ├── META-INF/       # Auto-generated SQL files
│   │       ├── init_mysql.sql      # MySQL initialization script
│   │       └── init_postgresql.sql # PostgreSQL initialization script
│   └── test/
│       └── java/
│           └── example/
│               └── generated/  # Auto-generated test code
```

## Generated Tables

Code is generated from the following four tables:

- `DEPARTMENT` - Department information
- `ADDRESS` - Address information
- `EMPLOYEE` - Employee information (with foreign key constraints to DEPARTMENT and ADDRESS)
- `COMP_KEY_ADDRESS` - Address information with composite primary key

## Usage

### Code Generation

#### Generate code for MySQL
```bash
./gradlew domaCodeGenMysqlAll
```

#### Generate code for PostgreSQL
```bash
./gradlew domaCodeGenPostgresqlAll
```

### Clean up generated code
```bash
./gradlew clean
```

### Run tests
```bash
./gradlew test
```

## Configuration

The following configurations are set in `build.gradle.kts`:

### MySQL Configuration
- Uses Testcontainers MySQL
- Initialization script: `src/main/resources/init_mysql.sql`
- Generated packages:
  - Entities: `example.generated.mysql.entity`
  - DAOs: `example.generated.mysql.dao`

### PostgreSQL Configuration
- Uses Testcontainers PostgreSQL
- Initialization script: `src/main/resources/init_postgresql.sql`
- Generated packages:
  - Entities: `example.generated.postgresql.entity`
  - DAOs: `example.generated.postgresql.dao`

## Requirements

- Java 17 or higher
- Docker (required for running Testcontainers)

## References

- [Doma Official Documentation](https://doma.readthedocs.io/)
- [Doma Codegen Plugin](https://doma.readthedocs.io/en/stable/codegen/)
- [Testcontainers](https://www.testcontainers.org/)
