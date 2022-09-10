package com.simplekjl.stores.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.simplekjl.stores.ui.screens.restaurants.RestaurantsList
import com.simplekjl.stores.ui.screens.restaurants.RestaurantsViewModel
import org.koin.androidx.compose.viewModel


enum class NavPath(
    val route: String,
) {
    RestaurantsList(route = "restaurants_list"),
    RestaurantsSearch(route = "restaurants_search")
}


@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun AppNavHost(navHostController: NavHostController, scaffoldState: ScaffoldState) {
    NavHost(
        navController = navHostController,
        startDestination = NavPath.RestaurantsList.route
    ) {

        composable(NavPath.RestaurantsList.route) {
            val restaurantsViewModel: RestaurantsViewModel by viewModel()
            RestaurantsList(
                navHostController = navHostController,
                restaurantsViewModel = restaurantsViewModel
            )
        }

//        composable(NavPath.RestaurantsSearch.route) {
//            RestaurantSearch(
//                navHostController = navHostController
//            )
//        }

    }

}