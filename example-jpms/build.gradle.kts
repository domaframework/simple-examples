plugins {
    java
    application
}

tasks {
    compileJava {
        val aptOptions = extensions.getByType<com.diffplug.gradle.eclipse.apt.AptPlugin.AptOptions>()
        aptOptions.processorArgs =
            mapOf(
                "doma.domain.converters" to "example.jpms.domain.DomainConverterProvider",
            )
        // If you are not using Eclipse, you can simply write the above code as follows without using aptOptions;
        // options.compilerArgs.add("-Adoma.domain.converters=example.jpms.domain.DomainConverterProvider")
    }
}

application {
    mainClass.set("example.jpms_java.Main")
}

dependencies {
    implementation(project(":common"))
}
