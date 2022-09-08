package com.simplekjl.domain

import com.simplekjl.domain.model.RestaurantDetails

interface StoresRepository {
    fun getAllStores(): List<RestaurantDetails>
}
