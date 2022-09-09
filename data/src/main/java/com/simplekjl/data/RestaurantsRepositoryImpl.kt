package com.simplekjl.data

import com.simplekjl.data.local.LocalSource
import com.simplekjl.data.mapper.toModel
import com.simplekjl.domain.repository.RestaurantsRepository
import com.simplekjl.domain.model.RestaurantDetails

class RestaurantsRepositoryImpl(
    private val localSource: LocalSource
) : RestaurantsRepository {

    override fun getAllRestaurants(): List<RestaurantDetails> {
        return localSource.getRestaurants().toModel()
    }
}
