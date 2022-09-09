package com.simplekjl.data

import com.appmattus.kotlinfixture.kotlinFixture
import com.google.common.truth.Truth.assertThat
import com.simplekjl.data.local.LocalSource
import com.simplekjl.data.model.RestaurantDetailsRaw
import com.simplekjl.domain.model.RestaurantDetails
import com.simplekjl.domain.repository.RestaurantsRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue

internal class RestaurantsRepositoryImplTest {
    private val fixture = kotlinFixture()

    @MockK
    private lateinit var localSource: LocalSource

    private lateinit var repository: RestaurantsRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        repository = RestaurantsRepositoryImpl(localSource)
    }

    @Test
    fun `verify local source returns empty list`() {
        every { localSource.getRestaurants() } returns emptyList()
        assertTrue(repository.getAllRestaurants().isEmpty())
    }

    @Test
    fun `verify localSource returns Store details values`() {
        val listOfStores: List<RestaurantDetailsRaw> = fixture()
        every { localSource.getRestaurants() } returns listOfStores
        val result = repository.getAllRestaurants()
        assertTrue(result.isNotEmpty())
        assertThat(result.any()).isInstanceOf(RestaurantDetails::class.java)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}
