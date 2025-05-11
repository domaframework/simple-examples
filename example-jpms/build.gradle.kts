plugins {
    java
    application
}

application {
    mainClass.set("example.jpms_java.Main")
}

dependencies {
    implementation(project(":common"))
}

tasks {
    compileJava {
        // TODO: This is a workaround. JPMS-compatible modules can’t be built with the Doma Compile Plugin.
        options.sourcepath = files(sourceSets["main"].java.srcDirs + sourceSets["main"].resources.srcDirs)
    }
}
