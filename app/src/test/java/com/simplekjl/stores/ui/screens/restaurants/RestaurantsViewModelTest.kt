package com.simplekjl.stores.ui.screens.restaurants

import com.appmattus.kotlinfixture.Fixture
import com.appmattus.kotlinfixture.kotlinFixture
import com.simplekjl.domain.model.RestaurantDetails
import com.simplekjl.domain.model.Status
import com.simplekjl.domain.usecase.GetAllRestaurantsUseCase
import com.simplekjl.domain.utils.Result
import com.simplekjl.stores.ui.utils.MainCoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.random.Random

@OptIn(ExperimentalCoroutinesApi::class)
internal class RestaurantsViewModelTest {

    @MockK
    private lateinit var getAllRestaurantsUseCase: GetAllRestaurantsUseCase

    private lateinit var viewModel: RestaurantsViewModel

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val fixture: Fixture = kotlinFixture {
        // random status values for the fixture store
        factory { Status.values()[Random.nextInt(Status.values().size)] }
    }

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `when viewModel is initialized, useCase is trigger`() {
        coEvery { getAllRestaurantsUseCase.invoke(Unit) } returns Result.Success(emptyList())
        viewModel = RestaurantsViewModel(getAllRestaurantsUseCase)
        coVerify { getAllRestaurantsUseCase.invoke(Unit) }
    }

    @Test
    fun `when viewModel is initialized, restaurantsList is not empty`() {
        val result: List<RestaurantDetails> = fixture()
        coEvery { getAllRestaurantsUseCase.invoke(Unit) } returns Result.Success(result)
        viewModel = RestaurantsViewModel(getAllRestaurantsUseCase)
        assert(viewModel.restaurantsList.value.isNotEmpty())
    }

    @Test
    fun `when viewModel is initialized, restaurantsList is not empty and is sorted`() {
        coEvery { getAllRestaurantsUseCase.invoke(Unit) } returns Result.Success(fakeRestaurantList)
        viewModel = RestaurantsViewModel(getAllRestaurantsUseCase)
        assert(viewModel.restaurantsList.value.isNotEmpty())
        assert(viewModel.restaurantsList.value.first().status == Status.OPEN)
        assert(viewModel.restaurantsList.value.last().status == Status.CLOSED)
    }

    @Test
    fun `when createFiltersMap(array) then return map with same number of elements in order `() {
        val filters: Array<String> = fixture()
        viewModel = RestaurantsViewModel(getAllRestaurantsUseCase)
        val filtersMap = viewModel.createFilterMap(filters)
        assert(filtersMap.size == filters.size)
        assert(filtersMap[0] == filters[0])
        assert(filtersMap[filters.size - 1] == filters[filtersMap.size - 1])
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    private val fakeRestaurantList = listOf(
        RestaurantDetails(1, "", Status.OPEN, fixture()),
        RestaurantDetails(3, "", Status.CLOSED, fixture()),
        RestaurantDetails(1, "", Status.OPEN, fixture()),
        RestaurantDetails(3, "", Status.ORDER_AHEAD, fixture()),
        RestaurantDetails(1, "", Status.ORDER_AHEAD, fixture()),
        RestaurantDetails(3, "", Status.CLOSED, fixture()),
    )
}