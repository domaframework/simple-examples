plugins {
    java
}

tasks {
    compileJava {
        val aptOptions = extensions.getByType<com.diffplug.gradle.eclipse.apt.AptPlugin.AptOptions>()
        aptOptions.processorArgs = mapOf(
                "doma.domain.converters" to "example.dsl_style_java.domain.DomainConverterProvider"
        )
    }
}
