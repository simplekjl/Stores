package com.simplekjl.stores.ui.screens.restaurants

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simplekjl.domain.model.RestaurantDetails
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
            val result = getAllRestaurantsUseCase.invoke(Unit)
            when (result) {
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
        viewModelScope.launch {
            _restaurantList.value.apply {
                _restaurantList.emit(
                    when (filterId) {
                        1 /*best match h-m*/ -> {
                            sortedBy { it.sortingValues.bestMatch }
                        }
                        2 /*newest h-m*/ -> {
                            sortedBy { it.sortingValues.newest }
                        }
                        3 /*rating h-m*/ -> {
                            sortedBy { it.sortingValues.ratingAverage }
                        }
                        4 /*distance m-h*/ -> {
                            sortedBy { it.sortingValues.distance }
                        }
                        6 /*Popular  h-m*/ -> {
                            sortedBy { it.sortingValues.popularity }
                        }
                        7 /*price m-h*/ -> {
                            sortedBy { it.sortingValues.averageProductPrice }
                        }
                        8 /*delivery m-h*/ -> {
                            sortedBy { it.sortingValues.deliveryCost }
                        }
                        9 /*costs m-h*/ -> {
                            sortedBy { it.sortingValues.minCost }
                        }
                        else /*No filter */ -> {
                            sortedBy { it.status }
                        }
                    }
                )
            }
        }
    }
}