package vn.finance.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            AppTheme {
                FinanceNavHost(navController)
            }
        }
    }

    @Composable
    fun FinanceNavHost(navController: NavHostController) {
        NavHost(navController = navController, startDestination = onboardingApi.path) {
            composable(onboardingApi.path) {
                onboardingApi.OnboardingPage(navigateTo = {
                    navController.navigate(authenticationApi.path) {
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
                homeApi.HomePage()
            }
        }
    }
}
