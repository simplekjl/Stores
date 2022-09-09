package com.simplekjl.domain.repository

import com.simplekjl.domain.model.RestaurantDetails

interface StoresRepository {
    fun getAllStores(): List<RestaurantDetails>
}
