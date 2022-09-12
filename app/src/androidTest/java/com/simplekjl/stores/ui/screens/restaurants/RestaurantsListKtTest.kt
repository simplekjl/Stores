@file:OptIn(ExperimentalComposeUiApi::class)

package com.simplekjl.stores.ui.screens.restaurants

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.simplekjl.stores.R
import com.simplekjl.stores.ui.components.NoSearchResults
import com.simplekjl.stores.ui.components.SearchViewTopAppBar
import com.simplekjl.stores.ui.theme.StoresTheme
import org.junit.Rule
import org.junit.Test

internal class RestaurantsListKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    // use createAndroidComposeRule<YourActivity>() if you need access to

    @Test
    fun searchViewTopBarTest() {
        var title = ""
        composeTestRule.setContent {
            StoresTheme {
                title = stringResource(id = R.string.takeaway_title)
                SearchViewTopAppBar(title = R.string.takeaway_title, onSearchBarClick = {})
            }
        }

        composeTestRule.onNodeWithText(title).assertIsDisplayed()
        composeTestRule.onNodeWithTag("iconSearch").assertIsDisplayed()
        composeTestRule.onNodeWithTag("iconSearch").performClick()
    }

    @Test
    fun noResultsTest() {
        var noResults = ""
        composeTestRule.setContent {
            StoresTheme {
                noResults = stringResource(id = R.string.no_restaurants_found)
                NoSearchResults()
            }
        }

        composeTestRule.onNodeWithText(noResults).assertIsDisplayed()
    }

}