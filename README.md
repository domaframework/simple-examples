Simple Examples [![Java CI with Gradle](https://github.com/domaframework/simple-examples/workflows/Java%20CI%20with%20Gradle/badge.svg)](https://github.com/domaframework/simple-examples/actions?query=workflow%3A%22Java+CI+with+Gradle%22)
========================================

Simple examples for [Doma](https://github.com/domaframework/doma).

This repository includes the following examples:

* [dao-style-basic](dao-style-basic) - Shows basic DAO style. Written in Java 8.
* [dao-style-file](dao-style-file) - Uses files to store SQL templates. Written in Java 8.
* [dao-style-text](dao-style-text) - Uses Text Blocks to store SQL templates. Written in Java 15.
* [dsl-style-java](dsl-style-java) - Uses the Criteria API. Written in Java 8.
* [dsl-style-kotlin](dsl-style-kotlin) - Uses the Kotlin Criteria API. Written in Kotlin 1.4.

Clone this repository
---------------------

```bash
$ git clone https://github.com/domaframework/simple-examples.git
$ cd simple-examples
```

Java version
------------

You need Java 15 or later.

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

Before importing the project into Eclipse, run the following command:

```bash
$ ./gradlew cleanEclipse eclipse
```

The `eclipse` task generates Eclipse setting files.  
We recommend you write settings on build.gradle and run this task
whenever you have to change settings about annotation processing.
Never change Eclipse directly.
