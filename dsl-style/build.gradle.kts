plugins {
    java
}

tasks {
    compileJava {
        val aptOptions = extensions.getByType<com.diffplug.gradle.eclipse.apt.AptPlugin.AptOptions>()
        aptOptions.processorArgs = mapOf(
                "doma.domain.converters" to "example.dsl_style.domain.DomainConverterProvider"
        )
    }
}

dependencies {
    val domaVersion: String by project
    annotationProcessor("org.seasar.doma:doma-processor:${domaVersion}")
    implementation("org.seasar.doma:doma-core:${domaVersion}")
    implementation("org.seasar.doma:doma-slf4j:${domaVersion}")
}

val javaLanguageVersion: JavaLanguageVersion = JavaLanguageVersion.of(11)

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
