package com.simplekjl.domain

import com.simplekjl.domain.model.Restaurant

interface StoresRepository {
    fun getAllStores(): List<Restaurant>
}