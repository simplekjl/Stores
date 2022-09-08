package com.simplekjl.data

import android.content.res.AssetManager
import com.google.gson.Gson
import com.simplekjl.data.model.RestaurantDetailsRaw
import com.simplekjl.data.model.RestaurantsRaw
import com.simplekjl.data.model.SortingValuesRaw
import com.simplekjl.domain.StoresRepository
import com.simplekjl.domain.model.RestaurantDetails
import com.simplekjl.domain.model.SortingValues
import com.simplekjl.domain.model.Status
import com.simplekjl.domain.model.Status.CLOSED
import com.simplekjl.domain.model.Status.OPEN
import com.simplekjl.domain.model.Status.ORDER_AHEAD
import java.io.IOException
import org.koin.java.KoinJavaComponent.get


class StoresRepositoryImpl : StoresRepository {

    private val assetManager: AssetManager = get(AssetManager::class.java)

    override fun getAllStores(): List<RestaurantDetails> {
        return try {
            val jsonFileString = assetManager.open("consumer_mobile_sample.json").bufferedReader()
                .use { it.readText() }
            val result = Gson().fromJson(jsonFileString, RestaurantsRaw::class.java)
            result.restaurants.toModel()
        } catch (ioEx: IOException) {
            emptyList()
        }
    }
}

private fun List<RestaurantDetailsRaw>.toModel(): List<RestaurantDetails> {
    val finalList = mutableListOf<RestaurantDetails>()
    this.forEach { item ->
        finalList.add(
            RestaurantDetails(
                item.id,
                item.name,
                getStatus(item.status),
                getSortingValues(item.sortingValues)
            )
        )
    }
    return finalList
}

private fun getStatus(status: String): Status {
    return when (status) {
        "open" -> OPEN
        "order ahead" -> ORDER_AHEAD
        "closed" -> CLOSED
        else -> CLOSED
    }
}

private fun getSortingValues(item: SortingValuesRaw): SortingValues {
    return SortingValues(
        item.bestMatch,
        item.newest,
        item.ratingAverage,
        item.distance,
        item.popularity,
        item.averageProductPrice,
        item.deliveryCost,
        item.minCost
    )
}