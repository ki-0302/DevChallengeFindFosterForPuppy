package com.example.androiddevchallenge.ui.compose.overview

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.ui.compose.*
import com.example.androiddevchallenge.ui.theme.AppTheme

@Composable
fun OverviewScreen(darkTheme: Boolean = isSystemInDarkTheme()) {
    AppTheme(darkTheme = darkTheme) {
        Scaffold(
            topBar = { TopAppBar() },
            content = { }
        )
    }
}

@Preview(PREVIEW_DARK_THEME, widthDp = PREVIEW_WIDTH, heightDp = PREVIEW_HEIGHT)
@Composable
fun DarkThemeOverviewScreenPreview() {
    OverviewScreen(darkTheme = true)
}

@Preview(PREVIEW_LIGHT_THEME, widthDp = PREVIEW_WIDTH, heightDp = PREVIEW_HEIGHT)
@Composable
fun LightThemeOverviewScreenPreview() {
    OverviewScreen(darkTheme = false)
}
