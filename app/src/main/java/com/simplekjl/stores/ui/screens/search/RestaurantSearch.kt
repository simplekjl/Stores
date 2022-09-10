package com.simplekjl.stores.ui.screens.search

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.simplekjl.stores.R.string
import com.simplekjl.stores.ui.components.RestaurantItem
import com.simplekjl.stores.ui.components.SearchBarUI
import com.simplekjl.stores.utils.rememberFlowWithLifecycle


private const val TAG = "RestaurantsSearch"

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun RestaurantSearch(
    navHostController: NavHostController,
    searchViewModel: RestaurantSearchViewModel
) {
    val userSearchModelState by rememberFlowWithLifecycle(searchViewModel.restaurantSearchModelState)
        .collectAsState(initial = RestaurantSearchModelState.Empty)
    SearchBarUI(
        searchText = userSearchModelState.searchText,
        placeholderText = stringResource(string.search_restaurants_hint),
        onSearchTextChanged = { searchViewModel.onSearchTextChanged(it) },
        onClearClick = { searchViewModel.onClearClick() },
        onNavigateBack = {
            navHostController.popBackStack()
        },
        matchesFound = userSearchModelState.restaurants.isNotEmpty()
    ) {

        LazyColumn {
            items(userSearchModelState.restaurants) { item ->
                RestaurantItem(restaurantDetails = item)
            }
        }
    }
}