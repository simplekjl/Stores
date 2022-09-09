package com.simplekjl.data.mapper

import com.appmattus.kotlinfixture.kotlinFixture
import com.simplekjl.data.model.RestaurantDetailsRaw
import org.junit.Test

internal class RestaurantRawMapperTest {

    private val fixture = kotlinFixture()

    @Test
    fun `listOf(RestaurantDetailsRaw) to model`() {
        val testData: List<RestaurantDetailsRaw> = fixture()
        val result = testData.toModel()
        assert(result.isNotEmpty())
    }
}
