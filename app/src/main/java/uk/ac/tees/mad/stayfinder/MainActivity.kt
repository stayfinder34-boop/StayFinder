package uk.ac.tees.mad.stayfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import uk.ac.tees.mad.stayfinder.navigation.AppNavHost
import uk.ac.tees.mad.stayfinder.navigation.NavRoutes
import uk.ac.tees.mad.stayfinder.ui.screens.auth.AuthScreen
import uk.ac.tees.mad.stayfinder.ui.screens.splash.SplashUiState
import uk.ac.tees.mad.stayfinder.ui.screens.splash.SplashViewModel
import uk.ac.tees.mad.stayfinder.ui.theme.StayFinderTheme
import androidx.compose.runtime.collectAsState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        val splashViewModel : SplashViewModel by viewModels()

        splashScreen.setKeepOnScreenCondition {
            splashViewModel.splashUiState.value is SplashUiState.Loading
        }

        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()

            val startDestination = when(splashViewModel
                .splashUiState
                .collectAsState()
                .value){
                SplashUiState.Loading -> null
                SplashUiState.NavigateToAuth -> NavRoutes.Auth
                SplashUiState.NavigateToHome -> NavRoutes.Home
            }

            startDestination?.let {
                StayFinderTheme {
                    AppNavHost(
                        startDestination = startDestination,
                        navController = navController
                    )
                }
            }
        }
    }
}
