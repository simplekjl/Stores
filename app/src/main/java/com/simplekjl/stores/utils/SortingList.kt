package com.simplekjl.stores.utils

/*

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
 *
 */