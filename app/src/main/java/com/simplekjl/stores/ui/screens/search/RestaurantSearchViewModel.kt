package com.simplekjl.stores.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simplekjl.domain.model.RestaurantDetails
import com.simplekjl.domain.usecase.GetAllRestaurantsUseCase
import com.simplekjl.domain.utils.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class RestaurantSearchViewModel(private val getAllRestaurantsUseCase: GetAllRestaurantsUseCase) :
    ViewModel() {
    private var restaurants: ArrayList<RestaurantDetails> = ArrayList()
    private val searchText: MutableStateFlow<String> = MutableStateFlow("")
    private var showProgressBar: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private var matchedRestaurants: MutableStateFlow<List<RestaurantDetails>> =
        MutableStateFlow(arrayListOf())

    val restaurantSearchModelState = combine(
        searchText,
        matchedRestaurants,
        showProgressBar
    ) { text, matchedRestaurants, showProgress ->

        RestaurantSearchModelState(
            text,
            matchedRestaurants,
            showProgress
        )
    }


    init {
        // TODO improve with a specific use case
        fetchAvailableRestaurants()
    }

    private fun fetchAvailableRestaurants() {
        viewModelScope.launch {
            val result = getAllRestaurantsUseCase.invoke(Unit)

            if (result is Result.Success) {
                restaurants.addAll(result.data)
            }
        }
    }

    fun onSearchTextChanged(changedSearchText: String) {
        searchText.value = changedSearchText
        if (changedSearchText.isEmpty()) {
            matchedRestaurants.value = arrayListOf()
            return
        }
        val usersFromSearch = restaurants.filter { item ->
            item.name.contains(changedSearchText, true) ||
                item.name.contains(changedSearchText, true) || item.name.contains(
                changedSearchText,
                true
            )
        }
        matchedRestaurants.value = usersFromSearch
    }

    fun onClearClick() {
        searchText.value = ""
        matchedRestaurants.value = arrayListOf()
    }

}
