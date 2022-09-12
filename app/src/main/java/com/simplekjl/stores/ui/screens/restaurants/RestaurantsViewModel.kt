package com.simplekjl.stores.ui.screens.restaurants

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simplekjl.domain.model.RestaurantDetails
import com.simplekjl.domain.model.Status
import com.simplekjl.domain.usecase.GetAllRestaurantsUseCase
import com.simplekjl.domain.utils.Result.Error
import com.simplekjl.domain.utils.Result.Success
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RestaurantsViewModel(private val getAllRestaurantsUseCase: GetAllRestaurantsUseCase) :
    ViewModel() {

    private val _restaurantList = MutableStateFlow<List<RestaurantDetails>>(emptyList())
    val restaurantsList: StateFlow<List<RestaurantDetails>> = _restaurantList
    private val filtersAvailable = mutableMapOf<Int, String>()

    init {
        viewModelScope.launch {
            when (val result = getAllRestaurantsUseCase.invoke(Unit)) {
                is Error -> {/*nothing at the moment*/
                }
                is Success -> _restaurantList.emit(result.data)
            }
        }
    }

    fun createFilterMap(filterList: Array<String>): Map<Int, String> {
        val filtersMap = mutableMapOf<Int, String>()
        filterList.forEachIndexed { index, s -> filtersMap[index] = s }
        filtersAvailable.putAll(filtersMap)
        return filtersMap
    }

    fun applyFilterToRestaurantList(filterId: Int) {
        val orderedAndFilteredList = mutableListOf<RestaurantDetails>()
        val openRestaurants = mutableListOf<RestaurantDetails>()
        val orderRestaurants = mutableListOf<RestaurantDetails>()
        val closedRestaurants = mutableListOf<RestaurantDetails>()
        viewModelScope.launch {
            openRestaurants.addAll(_restaurantList.value.filter { it.status == Status.OPEN })
            orderRestaurants.addAll(_restaurantList.value.filter { it.status == Status.ORDER_AHEAD })
            closedRestaurants.addAll(_restaurantList.value.filter { it.status == Status.CLOSED })

            orderedAndFilteredList.addAll(applyCurrentFilterToSublist(openRestaurants, filterId))
            orderedAndFilteredList.addAll(applyCurrentFilterToSublist(orderRestaurants, filterId))
            orderedAndFilteredList.addAll(applyCurrentFilterToSublist(closedRestaurants, filterId))

            _restaurantList.emit(orderedAndFilteredList)
        }
    }

    private fun applyCurrentFilterToSublist(
        list: List<RestaurantDetails>,
        filter: Int
    ): List<RestaurantDetails> {
        return list.run {
            when (filter) {
                1 /*best match*/ -> {
                    sortedByDescending { it.sortingValues.bestMatch }
                }
                2 /*newest*/ -> {
                    sortedByDescending { it.sortingValues.newest }
                }
                3 /*rating*/ -> {
                    sortedByDescending { it.sortingValues.ratingAverage }
                }
                4 /*distance*/ -> {
                    sortedBy { it.sortingValues.distance }
                }
                5 /*Popular*/ -> {
                    sortedByDescending { it.sortingValues.popularity }
                }
                6 /*price*/ -> {
                    sortedBy { it.sortingValues.averageProductPrice }
                }
                7 /*delivery*/ -> {
                    sortedBy { it.sortingValues.deliveryCost }
                }
                8 /*costs*/ -> {
                    sortedBy { it.sortingValues.minCost }
                }
                else -> {
                    sortedBy { it.status }
                }
            }
        }
    }
}