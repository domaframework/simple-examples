rootProject.name = "simple-examples"

pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
    }
}

include("common")
include("common-testing")

include("example-sql-file")
include("example-sql-annotation")
include("example-criteria")
include("example-jpms")
include("example-geometric-type")
include("example-codegen-plugin")
