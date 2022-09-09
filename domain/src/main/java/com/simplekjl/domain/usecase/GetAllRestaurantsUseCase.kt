package com.simplekjl.domain.usecase

import com.simplekjl.domain.repository.RestaurantsRepository
import com.simplekjl.domain.utils.SuspendUseCase
import kotlinx.coroutines.CoroutineDispatcher

class GetAllRestaurantsUseCase(
    private val dispatcher: CoroutineDispatcher,
    private val repository: RestaurantsRepository
) :
    SuspendUseCase<Unit, Unit>(dispatcher) {
    override suspend fun execute(parameters: Unit) {
        repository.getAllRestaurants()
    }
}