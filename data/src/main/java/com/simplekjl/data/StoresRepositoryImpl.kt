package com.simplekjl.data

import com.simplekjl.domain.LocalSource
import com.simplekjl.domain.StoresRepository
import com.simplekjl.domain.model.RestaurantDetails

class StoresRepositoryImpl(
    private val localSource: LocalSource
) : StoresRepository {

    override fun getAllStores(): List<RestaurantDetails> {
        return localSource.getStores()
    }
}
