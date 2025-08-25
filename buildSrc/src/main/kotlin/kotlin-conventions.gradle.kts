import org.gradle.kotlin.dsl.configureEach
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTargetWithTests
import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType
import org.jetbrains.kotlin.gradle.targets.js.testing.KotlinJsTest

plugins {
    org.jetbrains.kotlin.multiplatform
    org.jetbrains.kotlinx.`binary-compatibility-validator`
    org.jetbrains.dokka
}

repositories {
    mavenCentral()
}

kotlin {
    explicitApi()

    compilerOptions {
        commonCompilerOptions()
        optIn.addAll(kaidOptIns)
    }
    jvm {
        compilerOptions {
            jvmCompilerOptions()
        }
    }
    js {
        nodejs()
        useCommonJs()
    }

    applyDefaultHierarchyTemplate()
}

apiValidation {
    bvcOptions()
}

tasks {
    withType<Test>().configureEach {
        useJUnitPlatform()
    }

    withType<KotlinJsTest>().configureEach {
        environment("PROJECT_ROOT", rootProject.projectDir.absolutePath)
    }
}

