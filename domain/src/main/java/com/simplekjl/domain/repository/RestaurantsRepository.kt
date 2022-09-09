package com.simplekjl.domain.repository

import com.simplekjl.domain.model.RestaurantDetails

interface RestaurantsRepository {
    fun getAllRestaurants(): List<RestaurantDetails>
}
