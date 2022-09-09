package com.simplekjl.domain.usecase

import com.simplekjl.domain.repository.StoresRepository
import com.simplekjl.domain.utils.SuspendUseCase
import kotlinx.coroutines.CoroutineDispatcher

class GetAllRestaurantsUseCase(
    private val dispatcher: CoroutineDispatcher,
    private val repository: StoresRepository
) :
    SuspendUseCase<Unit, Unit>(dispatcher) {
    override suspend fun execute(parameters: Unit) {
        repository.getAllStores()
    }
}