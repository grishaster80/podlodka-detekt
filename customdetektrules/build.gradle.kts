plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

version = "3.4"

kotlin {
    jvmToolchain(17)
}

dependencies {
    compileOnly(libs.detekt.api)
    testImplementation(libs.detekt.test)
    testRuntimeOnly(libs.test.junit.jupiter.engine)
}

tasks.withType<Test>{
    useJUnitPlatform()
}

