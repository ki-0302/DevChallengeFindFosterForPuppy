/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.model.Puppy
import com.example.androiddevchallenge.ui.compose.ComposePreviewData
import com.example.androiddevchallenge.ui.compose.PREVIEW_DARK_THEME
import com.example.androiddevchallenge.ui.compose.PREVIEW_HEIGHT
import com.example.androiddevchallenge.ui.compose.PREVIEW_LIGHT_THEME
import com.example.androiddevchallenge.ui.compose.PREVIEW_WIDTH
import com.example.androiddevchallenge.ui.compose.ScreenConfiguration
import com.example.androiddevchallenge.ui.compose.detail.DetailScreen
import com.example.androiddevchallenge.ui.compose.overview.OverviewScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val screenConfiguration = ScreenConfiguration(
            orientation = resources.configuration.orientation,
            screenWidthDp = resources.configuration.screenWidthDp,
            screenHeightDp = resources.configuration.screenHeightDp, // excluding system ui.
            density = resources.displayMetrics.density,
            scaledDensity = resources.displayMetrics.scaledDensity
        )

        val puppies = ComposePreviewData.puppies

        setContent {
            AppNavigation(screenConfiguration = screenConfiguration, puppies = puppies)
        }
    }
}

@Composable
fun AppNavigation(
    screenConfiguration: ScreenConfiguration,
    puppies: List<Puppy>
) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "overview") {
        composable("overview") {
            OverviewScreen(
                navController = navController,
                screenConfiguration = screenConfiguration,
                puppies = puppies
            )
        }
        composable("detail/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toInt() ?: 0
            DetailScreen(
                navController = navController,
                id = id
            )
        }
    }
}

@Preview(PREVIEW_DARK_THEME, widthDp = PREVIEW_WIDTH, heightDp = PREVIEW_HEIGHT)
@Composable
fun DarkThemeOverviewScreenPreview() {
    OverviewScreenPreview(darkTheme = true)
}

@Preview(PREVIEW_LIGHT_THEME, widthDp = PREVIEW_WIDTH, heightDp = PREVIEW_HEIGHT)
@Composable
fun LightThemeOverviewScreenPreview() {
    OverviewScreenPreview(darkTheme = false)
}

@Composable
fun OverviewScreenPreview(darkTheme: Boolean) {
    OverviewScreen(
        navController = rememberNavController(),
        darkTheme = false,
        screenConfiguration = ComposePreviewData.screenConfiguration,
        puppies = ComposePreviewData.puppies
    )
}
