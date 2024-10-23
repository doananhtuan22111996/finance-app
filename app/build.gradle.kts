import org.gradle.kotlin.dsl.support.kotlinCompilerOptions

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    id("kotlin-kapt")
    alias(libs.plugins.androidHilt)
//    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = Configs.namespace
    compileSdk = Configs.compileSdk

    defaultConfig {
        applicationId = Configs.applicationId
        minSdk = Configs.minSdk
        targetSdk = Configs.targetSdk
        versionCode = Configs.versionCode
        versionName = Configs.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Configs.javaVersion
        targetCompatibility = Configs.javaVersion
    }
    kotlinOptions {
        jvmTarget = Configs.jvmTarget
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Configs.kotlinCompilerExtensionVersion
    }
}

dependencies {
    implementation(fnlibs.financeTheme)
    implementation(fnlibs.financeLaunch)
    implementation(fnlibs.financeNavigation)
    implementation(fnlibs.financeOnboarding)
    implementation(fnlibs.financeAuthentication)
    implementation(fnlibs.financeAuthenticationBusiness)
    implementation(fnlibs.financeHome)
    implementation(fnlibs.financeHomeBusiness)

    implementation(libs.bundles.coreAndroidComponents)
    implementation(platform(libs.androidxComposeBom))
    implementation(libs.bundles.jetpackComposeComponents)
    implementation(libs.androidxHilt)
    kapt(libs.androidxHiltCompiler)
    testImplementation(libs.bundles.testComponents)
    testImplementation(libs.bundles.composeTestComponents)
    androidTestImplementation(libs.bundles.androidTestComponents)

    implementation(fnlibs.ohteepee)
}

kapt {
    correctErrorTypes = true
}