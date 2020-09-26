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

dependencies {
    val domaVersion: String by project
    annotationProcessor("org.seasar.doma:doma-processor:${domaVersion}")
    implementation("org.seasar.doma:doma-core:${domaVersion}")
    implementation("org.seasar.doma:doma-slf4j:${domaVersion}")
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
