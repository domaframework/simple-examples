plugins {
    id("java")
}

dependencies {
    implementation(libs.jdbc.postgresql)
    testImplementation(platform(libs.testcontainers.bom))
    testImplementation(libs.testcontainers.postgresql)
}

tasks {
    compileJava {
        val aptOptions = extensions.getByType<com.diffplug.gradle.eclipse.apt.AptPlugin.AptOptions>()
        aptOptions.processorArgs =
            mapOf(
                "doma.domain.converters" to "example.geometric.type.domain.DomainConverterProvider",
            )
        // If you are not using Eclipse, you can simply write the above code as follows without using aptOptions;
        // options.compilerArgs.add("-Adoma.domain.converters=example.geometric.type.domain.DomainConverterProvider")
    }
}
