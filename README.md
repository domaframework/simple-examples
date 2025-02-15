Simple Examples [![Java CI with Gradle](https://github.com/domaframework/simple-examples/workflows/Java%20CI%20with%20Gradle/badge.svg)](https://github.com/domaframework/simple-examples/actions?query=workflow%3A%22Java+CI+with+Gradle%22)
========================================

Simple examples for [Doma](https://github.com/domaframework/doma).

This repository includes the following examples:

* [example-sql-file](example-sql-file) - Uses SQL files to store SQL templates.
* [example-sql-annotation](example-sql-annotation) - Uses SQL annotations to store SQL templates.
* [example-criteria](example-criteria) - Uses the Criteria API.
* [example-jpms](example-jpms) - Uses the Java Platform Module System (JPMS).

ER diagram
---------------------

The ER diagram for the database used in the example projects is shown below.

```mermaid
erDiagram
    DEPARTMENT {
      INTEGER id PK "not null"
      VARCHAR name "not null"
      INTEGER version "not null"
    }
    EMPLOYEE {
      INTEGER id PK "not null"
      VARCHAR name "not null"
      INTEGER age "not null"
      INTEGER salary
      VARCHAR job_type
      TIMESTAMP hiredate
      INTEGER department_id
      INTEGER version "not null"
      TIMESTAMP inserttimestamp
      TIMESTAMP updatetimestamp
    }
    USER {
      INT id PK "auto_increment"
      VARCHAR name "not null"
      VARCHAR email "unique not null"
      TIMESTAMP created_at "default current_timestamp"
      INT version "default 0 not null"
    }
    ROLE {
      INT id PK "auto_increment"
      VARCHAR name "unique not null"
      INT version "default 0 not null"
    }
    USER_ROLE {
      INT id PK "auto_increment"
      INT user_id "not null"
      INT role_id "not null"
      INT version "default 0 not null"
    }
    PRODUCT {
      INT id PK "auto_increment"
      VARCHAR name "not null"
      DECIMAL price "not null"
      INT stock_quantity "not null"
      TIMESTAMP created_at "default current_timestamp"
      INT version "default 0 not null"
    }
    CATEGORY {
      INT id PK "auto_increment"
      VARCHAR name "unique not null"
      INT version "default 0 not null"
    }
    PRODUCT_CATEGORY {
      INT id PK "auto_increment"
      INT product_id "not null"
      INT category_id "not null"
      INT version "default 0 not null"
    }
    ORDER {
      INT id PK "auto_increment"
      INT user_id "not null"
      TIMESTAMP order_date "default current_timestamp"
      VARCHAR status "not null"
      INT version "default 0 not null"
    }
    ORDER_ITEM {
      INT id PK "auto_increment"
      INT order_id "not null"
      INT product_id "not null"
      INT quantity "not null"
      DECIMAL price "not null"
      INT version "default 0 not null"
    }
    PAYMENT {
      INT id PK "auto_increment"
      INT order_id "unique not null"
      DECIMAL amount "not null"
      TIMESTAMP payment_date "default current_timestamp"
      INT version "default 0 not null"
    }
    REVIEW {
      INT id PK "auto_increment"
      INT user_id "not null"
      INT product_id "not null"
      INT rating "check: 1-5"
      TEXT comment
      TIMESTAMP created_at "default current_timestamp"
      INT version "default 0 not null"
    }

    EMPLOYEE }|..|| DEPARTMENT : belongs_to
    USER_ROLE }|..|| USER : "user_id"
    USER_ROLE }|..|| ROLE : "role_id"
    PRODUCT_CATEGORY }|..|| PRODUCT : "product_id"
    PRODUCT_CATEGORY }|..|| CATEGORY : "category_id"
    ORDER }|..|| USER : "user_id"
    ORDER_ITEM }|..|| ORDER : "order_id"
    ORDER_ITEM }|..|| PRODUCT : "product_id"
    PAYMENT ||--|| ORDER : "order_id"
    REVIEW }|..|| USER : "user_id"
    REVIEW }|..|| PRODUCT : "product_id"
```

Clone this repository
---------------------

```bash
$ git clone https://github.com/domaframework/simple-examples.git
$ cd simple-examples
```

Java version
------------

You need Java 17.

Build
-----

```bash
$ ./gradlew build
```

Import the project into your IDE
--------------------------------

### IDEA

Import the project as a Gradle project.

### Eclipse

Import the project as a Eclipse project.
