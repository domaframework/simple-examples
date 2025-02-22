plugins {
    java
}

tasks {
    compileJava {
        val aptOptions = extensions.getByType<com.diffplug.gradle.eclipse.apt.AptPlugin.AptOptions>()
        aptOptions.processorArgs =
            mapOf(
                "doma.domain.converters" to "example.common.domain.DomainConverterProvider",
            )
        // If you are not using Eclipse, you can simply write the above code as follows without using aptOptions;
        // options.compilerArgs.add("-Adoma.domain.converters=example.common.domain.DomainConverterProvider")
    }
}
