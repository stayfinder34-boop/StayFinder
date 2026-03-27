package uk.ac.tees.mad.stayfinder.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uk.ac.tees.mad.stayfinder.ui.screens.auth.AuthScreen
import uk.ac.tees.mad.stayfinder.ui.screens.home.HomeScreen

/**
 * App Navigation Host is basically a holder to navigation it will contain all the screens
 * and with the help of nav controller and NavRoutes every screen will be reachable from
 * every screen .
 * It is a composable
 */

@Composable
fun AppNavHost(startDestination: NavRoutes ,
               navController: NavHostController){

    NavHost(
        startDestination = startDestination.route,
        navController = navController
    ) {
        composable(
            NavRoutes.Auth.route
        ){
            AuthScreen(
                onNavigateHome = {
                    navController.navigate(NavRoutes.Home.route){
                        popUpTo(NavRoutes.Auth.route){
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(
            NavRoutes.Home.route
        ){
            HomeScreen()
        }

        composable(
            NavRoutes.Setting.route
        ){}

        composable(
            NavRoutes.Detail.route
        ){}
    }
}


/**
 * currently we are having four screen auth , home , setting , detail screen
 * as per the requirement in future we will add it
 */



