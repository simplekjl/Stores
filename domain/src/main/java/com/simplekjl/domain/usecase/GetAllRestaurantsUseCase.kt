package com.simplekjl.domain.usecase

import com.simplekjl.domain.model.RestaurantDetails
import com.simplekjl.domain.repository.RestaurantsRepository
import com.simplekjl.domain.utils.Result
import com.simplekjl.domain.utils.Result.Success
import com.simplekjl.domain.utils.SuspendUseCase
import kotlinx.coroutines.CoroutineDispatcher

/**
 * Returns the list of available restaurants sorted
 */
class GetAllRestaurantsUseCase(
    private val dispatcher: CoroutineDispatcher,
    private val repository: RestaurantsRepository
) :
    SuspendUseCase<Unit, Result<List<RestaurantDetails>>>(dispatcher) {
    override suspend fun execute(parameters: Unit): Result<List<RestaurantDetails>> {
        return Success(repository.getAllRestaurants().sortedBy { it.status })
    }
}
