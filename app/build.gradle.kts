import vn.finance.buildSrc.Configs

plugins {
    vn.core.plugins.androidApplication
}

android {
    namespace = Configs.NAMESPACE

    defaultConfig {
        applicationId = Configs.APPLICATION_ID
        versionCode = Configs.VERSION_CODE
        versionName = Configs.VERSION_NAME
    }
}

dependencies {
    implementation(libs.coreCompose)
    implementation(libs.coreDomain)
    implementation(libs.coreData)

    implementation(libs.financeTheme)
    implementation(libs.financeLaunch)
    implementation(libs.provideNetworking)

    // Onboarding
    implementation(libs.financeOnboarding)
    implementation(libs.financeOnboardingApi)

    // Authentication
    implementation(libs.financeAuthenticationPresentation)
    implementation(libs.financeAuthenticationBusiness)
    implementation(libs.financeAuthenticationApi)

    // Home
    implementation(libs.financeHomePresentation)
    implementation(libs.financeHomeBusiness)
    implementation(libs.financeHomeApi)

    // Statistic
    implementation(libs.financeStatisticPresentation)
    implementation(libs.financeStatisticBusiness)

    // Profile
    implementation(libs.financeProfilePresentation)
    implementation(libs.financeProfileBusiness)
}
