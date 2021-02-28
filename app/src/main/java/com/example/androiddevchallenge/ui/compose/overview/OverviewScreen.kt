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
package com.example.androiddevchallenge.ui.compose.overview

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.androiddevchallenge.OverviewScreenPreview
import com.example.androiddevchallenge.model.Puppy
import com.example.androiddevchallenge.ui.compose.PREVIEW_DARK_THEME
import com.example.androiddevchallenge.ui.compose.PREVIEW_HEIGHT
import com.example.androiddevchallenge.ui.compose.PREVIEW_LIGHT_THEME
import com.example.androiddevchallenge.ui.compose.PREVIEW_WIDTH
import com.example.androiddevchallenge.ui.compose.ScreenConfiguration
import com.example.androiddevchallenge.ui.compose.TopAppBar
import com.example.androiddevchallenge.ui.theme.AppTheme

@Composable
fun OverviewScreen(
    navController: NavController,
    darkTheme: Boolean = isSystemInDarkTheme(),
    screenConfiguration: ScreenConfiguration,
    puppies: List<Puppy>
) {
    AppTheme(darkTheme = darkTheme) {
        Scaffold(
            topBar = { TopAppBar() },
            content = {
                Column(modifier = Modifier.fillMaxHeight()) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        PuppyList(
                            navController = navController,
                            screenConfiguration = screenConfiguration,
                            puppies = puppies
                        )
                    }
                }
            }
        )
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
