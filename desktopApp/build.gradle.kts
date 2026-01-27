import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(projects.shared)

    implementation(libs.kotlinx.coroutines.swing)
    implementation(compose.desktop.currentOs)
}

compose.desktop {
    application {
        mainClass = "com.kmp.template.jvm.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.kmp.template.jvm"
            packageVersion = "1.0.0"
        }
    }
}