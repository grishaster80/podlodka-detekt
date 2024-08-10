plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
    //alias(libs.plugins.detekt)
}

version = "2.8"

kotlin {
    jvmToolchain(17)
}

dependencies {
    compileOnly(libs.detekt.api)
    testImplementation(libs.detekt.test)
    testRuntimeOnly(libs.test.junit.jupiter.engine)
}

