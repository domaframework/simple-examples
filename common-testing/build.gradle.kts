plugins {
    id("java")
}

dependencies {
    implementation(project(":common"))
    implementation(platform(libs.junit.bom))
    implementation(libs.junit.jupiter.api)
}
