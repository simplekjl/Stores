package com.simplekjl.data.local

import android.content.res.AssetManager
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.simplekjl.data.model.RestaurantDetailsRaw
import com.simplekjl.data.model.RestaurantsRaw
import java.io.IOException

class LocalSourceImpl(
    private val assetManager: AssetManager
) : LocalSource {
    override fun getStores(): List<RestaurantDetailsRaw> {
        return try {
            val storesString = readStoresJsonFile()
            val storeModels = parseStoresStringToModel(storesString)
            storeModels.restaurants
        } catch (ex: Exception) {
            emptyList()
        }
    }

    private fun readStoresJsonFile(): String {
        return try {
            assetManager.open("consumer_mobile_sample.json").bufferedReader()
                .use { it.readText() }
        } catch (ioEx: IOException) {
            ""
        }
    }

    private fun parseStoresStringToModel(storesString: String): RestaurantsRaw {
        return try {
            Gson().fromJson(storesString, RestaurantsRaw::class.java)
        } catch (ex: JsonSyntaxException) {
            RestaurantsRaw(emptyList())
        }
    }
}
