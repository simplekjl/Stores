package com.simplekjl.data.model

import com.google.gson.annotations.SerializedName

data class RestaurantsRaw(
    @SerializedName("restaurants")
    val restaurants: List<RestaurantDetailsRaw>
)

data class RestaurantDetailsRaw(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("sortingValues")
    val sortingValues: SortingValuesRaw,
)

data class SortingValuesRaw(
    @SerializedName("bestMatch")
    val bestMatch: Double,
    @SerializedName("newest")
    val newest: Double,
    @SerializedName("ratingAverage")
    val ratingAverage: Double,
    @SerializedName("distance")
    val distance: Int,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("averageProductPrice")
    val averageProductPrice: Int,
    @SerializedName("deliveryCost")
    val deliveryCost: Int,
    @SerializedName("minCost")
    val minCost: Int
)
