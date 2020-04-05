Simple Examples [![Build Status](https://travis-ci.org/domaframework/simple-examples.png?branch=master)](https://travis-ci.org/domaframework/simple-examples)
========================================

Simple examples for [Doma](https://github.com/domaframework/doma).

Clone this repository
---------------------

```bash
$ git clone https://github.com/domaframework/simple-examples.git
$ cd simple-examples
```

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

License
-------

Apache License, Version 2.0
