package com.simplekjl.stores.ui.screens.restaurants

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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
import com.simplekjl.stores.ui.components.FilterSpinner
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
        Row(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                stringResource(R.string.restaurant_around_label),
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(8.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.weight(1f))
            FilterSpinner(
                restaurantsViewModel.createFilterMap(filterList),
                preselected = 0,
                onSelectionChanged = { restaurantsViewModel.applyFilterToRestaurantList(it) },
                modifier = Modifier.width(150.dp)
            )
        }
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(restaurants) { item ->
                RestaurantItem(restaurantDetails = item)
            }
        }
    }
}



