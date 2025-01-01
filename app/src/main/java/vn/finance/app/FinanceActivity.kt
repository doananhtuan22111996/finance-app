package vn.finance.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import vn.finance.api.SettingApi
import vn.finance.authentication.api.AuthenticationApi
import vn.finance.home.api.HomeApi
import vn.finance.onboarding.api.OnboardingApi
import vn.finance.theme.AppTheme
import javax.inject.Inject

@AndroidEntryPoint
class FinanceActivity : ComponentActivity() {

    @Inject
    lateinit var onboardingApi: OnboardingApi

    @Inject
    lateinit var authenticationApi: AuthenticationApi

    @Inject
    lateinit var homeApi: HomeApi

    @Inject
    lateinit var settingApi: SettingApi

    private val viewModel: FinanceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            val isDarkMode by viewModel.isDarkMode.collectAsStateWithLifecycle()
            val navController = rememberNavController()
            AppTheme(darkTheme = isDarkMode) {
                FinanceNavHost(navController)
            }
        }
    }

    @Composable
    fun FinanceNavHost(navController: NavHostController) {
        val isLoggedIn by viewModel.isLoggedIn.collectAsStateWithLifecycle()

        NavHost(navController = navController, startDestination = onboardingApi.path) {
            composable(onboardingApi.path) {
                onboardingApi.OnboardingPage(navigateTo = {
                    navController.navigate(if (isLoggedIn) homeApi.path else authenticationApi.path) {
                        popUpTo(onboardingApi.path) {
                            inclusive = true
                        }
                    }
                })
            }
            composable(authenticationApi.path) {
                authenticationApi.AuthenticationPage(onGotoHome = {
                    navController.navigate(homeApi.path) {
                        popUpTo(authenticationApi.path) {
                            inclusive = true
                        }
                    }
                })
            }
            composable(homeApi.path) {
                homeApi.HomePage(onGotoSetting = {
                    navController.navigate(settingApi.path)
                })
            }
            composable(settingApi.path) {
                settingApi.SettingPage(goBack = {
                    navController.popBackStack()
                }, onDarkModeChanged = {
                    viewModel.onDarkModeChanged(it)
                })
            }
        }
    }
}
