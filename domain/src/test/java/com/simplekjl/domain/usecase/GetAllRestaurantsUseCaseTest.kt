package com.simplekjl.domain.usecase

import com.simplekjl.domain.repository.RestaurantsRepository
import com.simplekjl.domain.utils.MainCoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class GetAllRestaurantsUseCaseTest {

    @MockK
    private lateinit var repository: RestaurantsRepository

    private lateinit var useCase: GetAllRestaurantsUseCase

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = GetAllRestaurantsUseCase(mainCoroutineRule.testDispatcher, repository)
    }

    @Test
    fun `when getAllRestaurants usecase repository is trigger`() {
        runBlockingTest {
            every { repository.getAllRestaurants() } returns emptyList()
            useCase.invoke(Unit)
            verify { repository.getAllRestaurants() }
        }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}
