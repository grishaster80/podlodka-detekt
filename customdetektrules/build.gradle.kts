plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

version = "1.9"

kotlin {
    jvmToolchain(8)
}

dependencies {
    compileOnly(libs.detekt.api)
    testImplementation(libs.detekt.test)
    testRuntimeOnly(libs.test.junit.jupiter.engine)
}

