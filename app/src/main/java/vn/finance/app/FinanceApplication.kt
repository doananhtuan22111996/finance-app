package vn.finance.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import finance.authentication.presentation.AuthenticationActivity
import finance.home.presentation.HomeActivity
import vn.finance.navigation.NavigationKey
import vn.finance.navigation.NavigationManager
import vn.finance.onboarding.OnboardingActivity
import javax.inject.Inject

@HiltAndroidApp
class FinanceApplication : Application() {
    @Inject
    lateinit var navigationManager: NavigationManager

    override fun onCreate() {
        super.onCreate()
        navigationManager.registerActivity(
            NavigationKey.Onboarding(),
            OnboardingActivity::class.java.name
        )
        navigationManager.registerActivity(
            NavigationKey.Authentication(),
            AuthenticationActivity::class.java.name
        )
        navigationManager.registerActivity(
            NavigationKey.Home(),
            HomeActivity::class.java.name
        )
    }
}