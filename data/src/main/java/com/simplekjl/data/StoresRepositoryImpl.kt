package com.simplekjl.data

import com.simplekjl.data.local.LocalSource
import com.simplekjl.data.mapper.toModel
import com.simplekjl.domain.repository.StoresRepository
import com.simplekjl.domain.model.RestaurantDetails

class StoresRepositoryImpl(
    private val localSource: LocalSource
) : StoresRepository {

    override fun getAllStores(): List<RestaurantDetails> {
        return localSource.getStores().toModel()
    }
}
