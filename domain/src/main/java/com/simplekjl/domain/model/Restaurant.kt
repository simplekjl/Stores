package com.simplekjl.domain.model

data class RestaurantDetails(
    val id: Int,
    val name: String,
    val status: Status,
    val sortingValues: SortingValues,
)

data class SortingValues(
    val bestMatch: Double,
    val newest: Double,
    val ratingAverage: Double,
    val distance: Int,
    val popularity: Double,
    val averageProductPrice: Int,
    val deliveryCost: Int,
    val minCost: Int
)

// this enum should be treated with care, the sorting depends on this order
enum class Status { OPEN, ORDER_AHEAD, CLOSED }
