package uk.ac.tees.mad.stayfinder.navigation

/**
 * Navigation Routes are basically the location of the particular screen
 * we do pass navigation routes to the composable in NavHost to tell it explicitly that
 * it is the location of the particular screen and nav controller navigate to location by using
 * the routes only
 *
 * so NavRoutes should be created using the sealed class because it makes it cleaner , readable ,
 * and safe since sealed class provides controlled inheritance .
 */

sealed class  NavRoutes(val route : String){
    object Home : NavRoutes("home")
    object Auth : NavRoutes("auth")
    object Detail : NavRoutes("detail/{id}"){
        fun getDetailRoute(id : Long) : String = "detail/$id"
    }
    object Setting : NavRoutes("setting")
}