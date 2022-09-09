package com.simplekjl.data

import android.content.res.AssetManager
import com.google.common.truth.Truth.assertThat
import com.simplekjl.data.local.LocalSource
import com.simplekjl.data.local.LocalSourceImpl
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayInputStream
import kotlin.test.assertTrue

internal class LocalSourceImplTest {

    @RelaxedMockK
    private lateinit var assetManager: AssetManager

    private lateinit var localSource: LocalSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        localSource = LocalSourceImpl(assetManager)
    }

    @Test
    fun `when read json file fail return empty list`() {
        every { assetManager.open(any()) } throws Exception()
        assertTrue(localSource.getStores().isEmpty())
    }

    @Test
    fun `when read json file succeed then return listOfRestaurants`() {
        every { assetManager.open(any()) } returns ByteArrayInputStream(
            TestData.restaurantsJsonString.toByteArray()
        )
        assertThat(localSource.getStores()).isNotEmpty()
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}
