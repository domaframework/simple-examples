plugins {
    id("java")
}

dependencies {
    implementation(libs.jdbc.postgresql)
    testImplementation(platform(libs.testcontainers.bom))
    testImplementation(libs.testcontainers.postgresql)
}
