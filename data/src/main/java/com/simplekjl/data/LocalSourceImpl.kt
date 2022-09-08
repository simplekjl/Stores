package com.simplekjl.data

import android.content.res.AssetManager
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.simplekjl.data.mapper.toModel
import com.simplekjl.data.model.RestaurantsRaw
import com.simplekjl.domain.LocalSource
import com.simplekjl.domain.model.RestaurantDetails
import java.io.IOException

class LocalSourceImpl(
    private val assetManager: AssetManager
) : LocalSource {
    override fun getStores(): List<RestaurantDetails> {
        return try {
            val storesString = readStoresJsonFile()
            val storeModels = parseStoresStringToModel(storesString)
            storeModels.toModel()
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
