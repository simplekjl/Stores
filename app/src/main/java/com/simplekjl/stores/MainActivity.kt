package com.simplekjl.stores

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.rememberNavController
import com.simplekjl.stores.navigation.AppNavHost
import com.simplekjl.stores.ui.theme.StoresTheme

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            StoresTheme {
                Surface(color = MaterialTheme.colors.background) {
                    AppNavHost(navController)
                }
            }
        }
    }
}
