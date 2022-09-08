package com.simplekjl.data

import com.appmattus.kotlinfixture.kotlinFixture
import com.google.common.truth.Truth.assertThat
import com.simplekjl.data.model.RestaurantDetailsRaw
import com.simplekjl.domain.StoresRepository
import com.simplekjl.domain.model.RestaurantDetails
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue

internal class StoresRepositoryImplTest {
    private val fixture = kotlinFixture()

    @MockK
    private lateinit var localSource: LocalSource

    private lateinit var repository: StoresRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        repository = StoresRepositoryImpl(localSource)
    }

    @Test
    fun `verify local source returns empty list`() {
        every { localSource.getStores() } returns emptyList()
        assertTrue(repository.getAllStores().isEmpty())
    }

    @Test
    fun `verify localSource returns Store details values`() {
        val listOfStores: List<RestaurantDetailsRaw> = fixture()
        every { localSource.getStores() } returns listOfStores
        val result = repository.getAllStores()
        assertTrue(result.isNotEmpty())
        assertThat(result.any()).isInstanceOf(RestaurantDetails::class.java)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}
