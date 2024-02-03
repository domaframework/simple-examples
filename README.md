Simple Examples [![Java CI with Gradle](https://github.com/domaframework/simple-examples/workflows/Java%20CI%20with%20Gradle/badge.svg)](https://github.com/domaframework/simple-examples/actions?query=workflow%3A%22Java+CI+with+Gradle%22)
========================================

Simple examples for [Doma](https://github.com/domaframework/doma).

This repository includes the following examples:

* [dao-style-basic](dao-style-basic) - Shows basic DAO style. Written in Java 8.
* [dao-style-file](dao-style-file) - Uses files to store SQL templates. Written in Java 8.
* [dao-style-text](dao-style-text) - Uses Text Blocks to store SQL templates. Written in Java 17.
* [dsl-style-java](dsl-style-java) - Uses the Criteria API. Written in Java 8.
* [jpms-java](jpms-java) - Uses the Java Platform Module System (JPMS). Written in Java 17.

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

To import this project into Eclipse, adhere to the instructions outlined below.

**1. Run the Gradle command:**

```sh
$ ./gradlew cleanEclipse eclipse
```

**2. Navigate through the Eclipse menu and select:**

```
File > Import > General > Existing Projects into Workspace
```

It is important not to choose `File > Import > Gradle > Existing Gradle Project`.
