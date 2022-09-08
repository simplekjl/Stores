package com.simplekjl.data

import com.appmattus.kotlinfixture.kotlinFixture
import org.junit.Before

internal class StoresRepositoryImplTest {
    private val fixture = kotlinFixture()

    @RelaxedMockK
    private lateinit var dataSource: CatalogDataSource

    private lateinit var repository: CatalogRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        repository = CatalogRepositoryImpl(dataSource)
    }

}