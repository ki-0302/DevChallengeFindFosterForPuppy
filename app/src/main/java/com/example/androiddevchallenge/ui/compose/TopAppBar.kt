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
package com.example.androiddevchallenge.ui.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Pets
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.AppTheme

@Composable
fun TopAppBar(
    navController: NavController,
    title: String = stringResource(id = R.string.app_name),
    isRoot: Boolean = false
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        },
        navigationIcon = {
            if (isRoot) {
                Icon(
                    imageVector = Icons.Filled.Pets,
                    contentDescription = null,
                    modifier = Modifier.padding(start = 13.dp)
                )
            } else {
                IconButton(
                    onClick = {
                        if (!isRoot) {
                            navController.popBackStack()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        }
    )
}

@Preview("$PREVIEW_DARK_THEME Root", widthDp = PREVIEW_WIDTH, heightDp = 100)
@Composable
fun DarkThemeTopAppBarRootPreview() {
    TopAppBarPreview(darkTheme = true, isRoot = true)
}

@Preview("$PREVIEW_LIGHT_THEME Root", widthDp = PREVIEW_WIDTH, heightDp = 100)
@Composable
fun LightThemeTopAppBarRootPreview() {
    TopAppBarPreview(darkTheme = false, isRoot = true)
}

@Preview("$PREVIEW_DARK_THEME Child", widthDp = PREVIEW_WIDTH, heightDp = 100)
@Composable
fun DarkThemeTopAppBarChildPreview() {
    TopAppBarPreview(darkTheme = true, isRoot = false)
}

@Preview("$PREVIEW_LIGHT_THEME Child", widthDp = PREVIEW_WIDTH, heightDp = 100)
@Composable
fun LightThemeTopAppBarChildPreview() {
    TopAppBarPreview(darkTheme = false, isRoot = false)
}

@Composable
fun TopAppBarPreview(darkTheme: Boolean, isRoot: Boolean) {
    AppTheme(darkTheme = darkTheme) {
        Scaffold(
            topBar = {
                TopAppBar(
                    navController = rememberNavController(),
                    isRoot = isRoot
                )
            },
            content = {}
        )
    }
}
