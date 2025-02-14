plugins {
    java
    alias(libs.plugins.eclipse.apt)
    alias(libs.plugins.spotless)
    alias(libs.plugins.doma.compile)
}

// Retain a reference to rootProject.libs to make the version catalog accessible within allprojects and subprojects.
// See https://github.com/gradle/gradle/issues/16708
val catalog = libs

subprojects {
    apply(plugin = "java")
    apply(plugin = catalog.plugins.eclipse.apt.get().pluginId)
    apply(plugin = catalog.plugins.spotless.get().pluginId)
    apply(plugin = catalog.plugins.doma.compile.get().pluginId)

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }

    tasks {
        withType<JavaCompile> {
            options.encoding = "UTF-8"
        }

        withType<Test> {
            useJUnitPlatform()
        }
    }

    repositories {
        mavenCentral()
        mavenLocal()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
    }

    dependencies {
        annotationProcessor(catalog.doma.processor)
        implementation(catalog.doma.core)
        implementation(catalog.doma.slf4j)
        runtimeOnly(catalog.logback.classic)
        runtimeOnly(catalog.jdbc.h2)
        testImplementation(catalog.junit.jupiter.api)
        testRuntimeOnly(catalog.junit.jupiter.engine)
    }

    eclipse {
        classpath {
            file {
                whenMerged {
                    val classpath = this as org.gradle.plugins.ide.eclipse.model.Classpath
                    val folder = org.gradle.plugins.ide.eclipse.model.SourceFolder(".apt_generated", "bin/main")
                    classpath.entries.add(folder)
                    val dir = file(folder.path)
                    if (!dir.exists()) {
                        dir.mkdir()
                    }
                }
            }
        }
        project {
            buildCommand("org.eclipse.buildship.core.gradleprojectbuilder")
            natures("org.eclipse.buildship.core.gradleprojectnature")
        }
        // Reset all Eclipse settings when "Refresh Gradle Project" is executed
        synchronizationTasks("cleanEclipse", "eclipse")
    }

    spotless {
        java {
            googleJavaFormat(catalog.google.java.format.get().version)
        }
    }
}
