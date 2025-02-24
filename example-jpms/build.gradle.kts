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
