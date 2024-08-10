import com.android.utils.TraceUtils.simpleId
import io.gitlab.arturbosch.detekt.Detekt

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.detekt)
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.compose) apply false
}

//repositories {
//    mavenCentral()
//}

dependencies {
    //detektPlugins(rootProject)
    detektPlugins(project(":customdetektrules"))
}

detekt {
    debug = true
    toolVersion = libs.versions.detekt.get()
    config.setFrom(file("config/detekt/detekt.yml"))
    buildUponDefaultConfig = true
    autoCorrect = true
}
subprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")

    detekt {
        debug = true
        toolVersion = "1.23.6"
        config.setFrom(rootProject.files("config/detekt/detekt.yml"))
        buildUponDefaultConfig = true
        autoCorrect = true
    }

//    tasks.withType<Detekt> {
//        jvmTarget = "1.8"
//        //dependsOn(":customdetektrules:build")
//    }
}

version = "1.9"

tasks.withType<Detekt> {
    jvmTarget = "1.8"
    dependsOn(":customdetektrules:build")
}

//subprojects {
//    apply{
//        plugin("io.gitlab.arturbosch.detekt")
//    }
//}
