package com.simplekjl.stores.ui.screens.search

import com.simplekjl.domain.model.RestaurantDetails


data class RestaurantSearchModelState(
    val searchText: String = "",
    val restaurants: List<RestaurantDetails> = arrayListOf(),
    val showProgressBar: Boolean = false
) {

    companion object {
        val Empty = RestaurantSearchModelState()
    }

}