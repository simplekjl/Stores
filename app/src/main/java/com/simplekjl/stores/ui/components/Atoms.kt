package com.simplekjl.stores.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simplekjl.domain.model.RestaurantDetails
import com.simplekjl.domain.model.SortingValues
import com.simplekjl.domain.model.Status.CLOSED
import com.simplekjl.domain.model.Status.OPEN
import com.simplekjl.domain.model.Status.ORDER_AHEAD
import com.simplekjl.stores.ui.theme.StoresTheme


@Preview
@Composable
fun RestaurantItemPreview() {
    val sortingValues = SortingValues(1.0, 23.0, 3.0, 32, 23.3, 90, 3, 0)
    val details = RestaurantDetails(1, "Los Altos", OPEN, sortingValues)
    StoresTheme {
        RestaurantItem(details)
    }
}

@Composable
fun RestaurantItem(
    restaurantDetails: RestaurantDetails,
    modifier: Modifier = Modifier,
) {
    val statusPair: Pair<String, Color> = when (restaurantDetails.status) {
        CLOSED -> "Closed" to Color.Red
        OPEN -> "Open" to Color.Green
        ORDER_AHEAD -> "Order" to Color.Cyan
    }
    Card(
        modifier = Modifier
            .height(120.dp)
            .fillMaxWidth(),
        content = {
            Row(
                modifier = modifier
                    .wrapContentHeight()
                    .wrapContentWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(modifier = Modifier.padding(start = 16.dp), text = restaurantDetails.name)
                Spacer(modifier = modifier.weight(1f))
                Text(
                    modifier = Modifier.padding(end = 16.dp),
                    text = statusPair.first,
                    color = statusPair.second
                )

            }
        })
}

@Preview
@Composable
fun FilterItemPreview() {
    StoresTheme {
        FilterItem(filterName = "Cheapest")

    }
}

@Composable
fun FilterItem(
    modifier: Modifier = Modifier,
    filterName: String,
) {
    val checkedState = remember { mutableStateOf(false) }
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checkedState.value,
            onCheckedChange = { checkedState.value = it }
        )
        Text(modifier = Modifier.padding(end = 8.dp), text = filterName)
    }
}
