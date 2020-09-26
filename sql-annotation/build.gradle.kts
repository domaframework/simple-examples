plugins {
    java
}

tasks {
    compileJava {
        val aptOptions = extensions.getByType<com.diffplug.gradle.eclipse.apt.AptPlugin.AptOptions>()
        aptOptions.processorArgs = mapOf(
                "doma.domain.converters" to "example.sql_annotation.domain.DomainConverterProvider"
        )
    }
}

val javaLanguageVersion: JavaLanguageVersion = JavaLanguageVersion.of(15)

java {
    toolchain {
        languageVersion.set(javaLanguageVersion)
    }
}

eclipse {
    jdt {
        javaRuntimeName = "JavaSE-$javaLanguageVersion"
    }
}
