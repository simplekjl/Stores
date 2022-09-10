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
}