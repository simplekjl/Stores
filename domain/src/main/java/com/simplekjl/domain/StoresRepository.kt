package com.simplekjl.domain

import com.simplekjl.domain.model.Business

interface StoresRepository {
    fun getAllStores(): List<Business>
}