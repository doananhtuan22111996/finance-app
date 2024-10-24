import org.gradle.api.JavaVersion

object Configs {
    const val namespace = "vn.finance.app"
    const val applicationId = "vn.finance.app"
    const val minSdk = 24
    const val targetSdk = 34
    const val compileSdk = 34
    val jvmTarget = JavaVersion.VERSION_11.toString()
    val javaVersion = JavaVersion.VERSION_11
    const val versionCode = 1
    const val versionName = "1.0.0"
    val kotlinCompilerExtensionVersion = "1.5.14"
}


