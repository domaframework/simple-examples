Simple Examples [![Java CI with Gradle](https://github.com/domaframework/simple-examples/workflows/Java%20CI%20with%20Gradle/badge.svg)](https://github.com/domaframework/simple-examples/actions?query=workflow%3A%22Java+CI+with+Gradle%22)
========================================

Simple examples for [Doma](https://github.com/domaframework/doma).

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
