plugins {
    java
}

tasks {
    compileJava {
        val aptOptions = extensions.getByType<com.diffplug.gradle.eclipse.apt.AptPlugin.AptOptions>()
        aptOptions.processorArgs = mapOf(
                "doma.domain.converters" to "example.dao_style_file.domain.DomainConverterProvider"
        )
    }
}

val javaLanguageVersion: JavaLanguageVersion = JavaLanguageVersion.of(8)

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
