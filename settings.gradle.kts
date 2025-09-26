plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "kaid-utils"
include("kaid-internal")
include("kaid-resource")
include("kaid-math")