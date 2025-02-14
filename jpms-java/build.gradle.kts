plugins {
    java
    application
}

tasks {
    compileJava {
        val aptOptions = extensions.getByType<com.diffplug.gradle.eclipse.apt.AptPlugin.AptOptions>()
        aptOptions.processorArgs =
            mapOf(
                "doma.domain.converters" to "example.jpms_java.domain.DomainConverterProvider",
            )
    }
}

application {
    mainClass.set("example.jpms_java.Main")
}

dependencies {
    implementation(project(":common"))
}
