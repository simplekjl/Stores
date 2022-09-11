package com.simplekjl.stores.ui.screens.restaurants

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.simplekjl.stores.ui.components.SearchViewTopAppBar
import com.simplekjl.stores.ui.theme.StoresTheme
import org.junit.Rule
import org.junit.Test

internal class RestaurantsListKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    // use createAndroidComposeRule<YourActivity>() if you need access to
    // an activity

    @Test
    fun searchViewTopBarTest() {
        composeTestRule.setContent {
            StoresTheme {
                SearchViewTopAppBar(onSearchBarClick = {})
            }
        }

        composeTestRule.onNodeWithText("Takeaway").assertIsDisplayed()
        composeTestRule.onNodeWithTag("iconSearch").assertIsDisplayed()
        composeTestRule.onNodeWithTag("iconSearch").performClick()

    }
}