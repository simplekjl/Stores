package com.simplekjl.stores.ui.screens.restaurants


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.simplekjl.stores.R
import com.simplekjl.stores.navigation.NavPath
import com.simplekjl.stores.ui.components.FilterItem
import com.simplekjl.stores.ui.components.RestaurantItem
import com.simplekjl.stores.ui.components.SearchViewDemoTopAppBar


@Composable
fun RestaurantsList(
    navHostController: NavHostController,
    restaurantsViewModel: RestaurantsViewModel
) {
    val filterList: Array<String> = stringArrayResource(id = R.array.restaurant_filters)
    val restaurants by restaurantsViewModel.restaurantsList.collectAsState()
    Column(modifier = Modifier.fillMaxSize()) {
        SearchViewDemoTopAppBar(onSearchBarClick = {
            navHostController.navigate(route = NavPath.RestaurantsSearch.route)
        })
        Text(
            stringResource(R.string.restaurant_around_label),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth()
        ) {
            items(filterList) { filter ->
                FilterItem(filterName = filter)
            }
        }
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(restaurants) { item ->
                RestaurantItem(restaurantDetails = item)
            }
        }
    }
}

