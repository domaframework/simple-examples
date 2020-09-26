plugins {
    kotlin("jvm")
    kotlin("kapt")
}

val javaLanguageVersion: JavaLanguageVersion = JavaLanguageVersion.of(11)

tasks {
    val jvmTarget = javaLanguageVersion.toString()

    compileKotlin {
        kotlinOptions.jvmTarget = jvmTarget
    }

    compileTestKotlin {
        kotlinOptions.jvmTarget = jvmTarget
    }
}

kapt {
    arguments { 
        arg("doma.domain.converters", "example.dsl_style_kotlin.domain.DomainConverterProvider")
    }
}

dependencies {
    val domaVersion: String by project
    kapt("org.seasar.doma:doma-processor:${domaVersion}")
    implementation("org.seasar.doma:doma-kotlin:${domaVersion}")
    implementation("org.seasar.doma:doma-slf4j:${domaVersion}")
}

java {
    toolchain {
        languageVersion.set(javaLanguageVersion)
    }
}

spotless {
    kotlin {
        ktlint("0.38.1")
        trimTrailingWhitespace()
        endWithNewline()
    }
}
