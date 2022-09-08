package com.simplekjl.data

import com.appmattus.kotlinfixture.kotlinFixture
import com.simplekjl.domain.LocalSource
import com.simplekjl.domain.StoresRepository
import com.simplekjl.domain.model.RestaurantDetails
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
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
        val listOfStores: List<RestaurantDetails> = fixture()
        every { localSource.getStores() } returns listOfStores
        assertTrue(repository.getAllStores().isNotEmpty())
    }
}
