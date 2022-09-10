package com.simplekjl.stores.ui.screens.search

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import com.simplekjl.stores.navigation.NavPath
import com.simplekjl.stores.ui.components.SearchBarUI
import com.simplekjl.stores.utils.rememberFlowWithLifecycle

/**
private const val TAG="UserSearchUI"

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun RestaurantSearch(navHostController: NavHostController /*add view model in the next step*/) {
    val userSearchModelState by rememberFlowWithLifecycle(userSearchViewModel.userSearchModelState)
        .collectAsState(initial = UserSearchModelState.Empty)
    SearchBarUI(
        searchText = userSearchModelState.searchText,
        placeholderText = "Search users",
        onSearchTextChanged = { userSearchViewModel.onSearchTextChanged(it) },
        onClearClick = { userSearchViewModel.onClearClick() },
        onNavigateBack = {
            navHostController.popBackStack()
        },
        matchesFound = userSearchModelState.users.isNotEmpty()
    ) {

        Users(users = userSearchModelState.users) { user ->
            navHostController.navigate(route = "${NavPath.UserDetail.route}?id=${user.id}")
        }
    }
}**/