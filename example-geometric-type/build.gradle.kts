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
    }
}
