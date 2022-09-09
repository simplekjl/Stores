package com.simplekjl.data.local

import com.simplekjl.data.model.RestaurantDetailsRaw

interface LocalSource {
    fun getStores(): List<RestaurantDetailsRaw>
}
