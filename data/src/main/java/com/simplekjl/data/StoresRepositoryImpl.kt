package com.simplekjl.data

import android.content.res.AssetManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.simplekjl.data.model.RestaurantsRaw
import com.simplekjl.domain.StoresRepository
import com.simplekjl.domain.model.Business
import java.io.IOException
import org.koin.java.KoinJavaComponent.get


class StoresRepositoryImpl : StoresRepository {

    private val assetManager: AssetManager = get(AssetManager::class.java)

    override fun getAllStores(): List<Business> {
        return try {
            val jsonFileString = assetManager.open("consumer_mobile_sample.json").bufferedReader()
                .use { it.readText() }
            val listCountryType = object : TypeToken<List<RestaurantsRaw>>() {}.type
            Gson().fromJson(jsonFileString, listCountryType)

        } catch (ioEx: IOException) {
            emptyList()
        }
    }
}