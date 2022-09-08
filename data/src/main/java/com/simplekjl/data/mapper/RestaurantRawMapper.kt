package com.simplekjl.data.mapper

import com.simplekjl.data.model.RestaurantsRaw
import com.simplekjl.data.model.SortingValuesRaw
import com.simplekjl.domain.model.RestaurantDetails
import com.simplekjl.domain.model.SortingValues
import com.simplekjl.domain.model.Status
import com.simplekjl.domain.model.Status.CLOSED
import com.simplekjl.domain.model.Status.OPEN
import com.simplekjl.domain.model.Status.ORDER_AHEAD

fun RestaurantsRaw.toModel(): List<RestaurantDetails> {
    val finalList = mutableListOf<RestaurantDetails>()
    this.restaurants.forEach { item ->
        finalList.add(
            RestaurantDetails(
                item.id,
                item.name,
                getStatus(item.status),
                getSortingValues(item.sortingValues)
            )
        )
    }
    return finalList
}

private fun getStatus(status: String): Status {
    return when (status) {
        "open" -> OPEN
        "order ahead" -> ORDER_AHEAD
        "closed" -> CLOSED
        else -> CLOSED
    }
}

private fun getSortingValues(item: SortingValuesRaw): SortingValues {
    return SortingValues(
        item.bestMatch,
        item.newest,
        item.ratingAverage,
        item.distance,
        item.popularity,
        item.averageProductPrice,
        item.deliveryCost,
        item.minCost
    )
}
