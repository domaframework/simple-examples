plugins {
    java
    application
}

tasks {
    compileJava {
        val aptOptions = extensions.getByType<com.diffplug.gradle.eclipse.apt.AptPlugin.AptOptions>()
        aptOptions.processorArgs = mapOf(
            "doma.domain.converters" to "example.jpms_java.domain.DomainConverterProvider"
        )
    }
}

dependencies {
    val domaVersion: String by project
    annotationProcessor("org.seasar.doma:doma-processor:${domaVersion}")
    implementation("org.seasar.doma:doma-core:${domaVersion}")
    implementation("org.seasar.doma:doma-slf4j:${domaVersion}")
}

val javaLanguageVersion: JavaLanguageVersion = JavaLanguageVersion.of(17)


application {
    mainClass.set("example.jpms_java.Main")
}

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
