plugins {
    id("com.diffplug.eclipse.apt") version "3.29.0" apply false
    id("com.diffplug.spotless") version "5.11.1" apply false
    id("org.seasar.doma.compile") version "1.1.0" apply false
    kotlin("jvm") version "1.4.10" apply false
    kotlin("kapt") version "1.4.10" apply false
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "com.diffplug.eclipse.apt")
    apply(plugin = "com.diffplug.spotless")
    apply(plugin = "org.seasar.doma.compile")

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
        val domaVersion: String by project
        "annotationProcessor"("org.seasar.doma:doma-processor:${domaVersion}")
        "implementation"("org.seasar.doma:doma-core:${domaVersion}")
        "implementation"("org.seasar.doma:doma-slf4j:${domaVersion}")
        "runtimeOnly"("ch.qos.logback:logback-classic:1.2.3")
        "runtimeOnly"("com.h2database:h2:1.4.200")
        "testImplementation"("org.junit.jupiter:junit-jupiter-api:5.7.1")
        "testRuntimeOnly"("org.junit.jupiter:junit-jupiter-engine:5.7.1")
    }
    
    configure<org.gradle.plugins.ide.eclipse.model.EclipseModel> {
        classpath {
            file {
                whenMerged {
                    val classpath = this as org.gradle.plugins.ide.eclipse.model.Classpath
                    classpath.entries.removeAll {
                        when (it) {
                            is org.gradle.plugins.ide.eclipse.model.Output -> it.path == ".apt_generated"
                            else -> false
                        }
                    }
                }
                withXml {
                    val node = asNode()
                    node.appendNode("classpathentry", mapOf("kind" to "src", "output" to "bin/main", "path" to ".apt_generated"))
                }
            }
        }
    }

    configure<com.diffplug.gradle.spotless.SpotlessExtension> {
        java {
            googleJavaFormat("1.9")
        }
    }
}
