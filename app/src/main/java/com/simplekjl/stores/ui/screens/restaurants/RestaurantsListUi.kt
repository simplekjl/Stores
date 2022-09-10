package com.simplekjl.stores.ui.screens.restaurants


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.simplekjl.stores.R
import com.simplekjl.stores.R.string
import com.simplekjl.stores.navigation.NavPath
import com.simplekjl.stores.ui.components.RestaurantItem


@Composable
fun RestaurantsList(
    navHostController: NavHostController,
    restaurantsViewModel: RestaurantsViewModel
) {
    val restaurants by restaurantsViewModel.restaurantsList.collectAsState()
    Column(modifier = Modifier.fillMaxSize()) {
        SearchViewDemoTopAppBar(onSearchBarClick = {
            navHostController.navigate(route = NavPath.RestaurantsSearch.route)
        })
        Text(
            stringResource(string.restaurant_around_label),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(restaurants) { item ->
                RestaurantItem(restaurantDetails = item)
            }
        }
    }
}

@Composable
fun SearchViewDemoTopAppBar(onSearchBarClick: () -> Unit) {
    TopAppBar(title = { Text(stringResource(R.string.takeaway_title)) }, actions = {
        IconButton(
            modifier = Modifier,
            onClick = { onSearchBarClick() }) {
            Icon(
                Icons.Filled.Search,
                contentDescription = null
            )
        }
    })
}

