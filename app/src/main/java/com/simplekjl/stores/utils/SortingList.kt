package com.simplekjl.stores.utils

/*

fun applyFilterToRestaurantList(filterId: Int) {
        val orderedAndFilteredList = mutableListOf<RestaurantDetails>()
        val openRestaurants = mutableListOf<RestaurantDetails>()
        val orderRestaurants = mutableListOf<RestaurantDetails>()
        val closedRestaurants = mutableListOf<RestaurantDetails>()
        viewModelScope.launch {
            openRestaurants.addAll(_restaurantList.value.filter { it.status == OPEN })
            orderRestaurants.addAll(_restaurantList.value.filter { it.status == ORDER_AHEAD })
            closedRestaurants.addAll(_restaurantList.value.filter { it.status == CLOSED })

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
        return list.apply {
            when (filter) {
                0 /*best match*/ -> {
                    sortedBy { it.sortingValues.bestMatch }
                }
                1 /*newest*/ -> {
                    sortedBy { it.sortingValues.newest }
                }
                2 /*rating*/ -> {
                    sortedBy { it.sortingValues.ratingAverage }
                }
                3 /*distance*/ -> {
                    sortedBy { it.sortingValues.distance }
                }
                4 /*Popular*/ -> {
                    sortedBy { it.sortingValues.popularity }
                }
                5 /*price*/ -> {
                    sortedBy { it.sortingValues.averageProductPrice }
                }
                6 /*delivery*/ -> {
                    sortedBy { it.sortingValues.deliveryCost }
                }
                7 /*costs*/ -> {
                    sortedBy { it.sortingValues.minCost }
                }
                else -> {
                    sortedBy { it.status }
                }
            }
        }
    }
 *
 */