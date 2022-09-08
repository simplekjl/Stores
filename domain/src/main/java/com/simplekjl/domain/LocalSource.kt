package com.simplekjl.domain

import com.simplekjl.domain.model.RestaurantDetails

interface LocalSource {
    fun getStores(): List<RestaurantDetails>
}
