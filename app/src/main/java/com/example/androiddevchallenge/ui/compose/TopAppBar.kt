package com.example.androiddevchallenge.ui.compose

import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.compose.overview.OverviewScreen

@Composable
fun TopAppBar(title: String = stringResource(id = R.string.app_name)) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            Icon(Icons.Filled.Menu, contentDescription = null)
        }
    )
}

@Preview(PREVIEW_DARK_THEME, widthDp = PREVIEW_WIDTH, heightDp = PREVIEW_HEIGHT)
@Composable
fun DarkThemeTopAppBarPreview() {
    OverviewScreen(darkTheme = true)
}

@Preview(PREVIEW_LIGHT_THEME, widthDp = PREVIEW_WIDTH, heightDp = PREVIEW_HEIGHT)
@Composable
fun LightThemeTopAppBarPreview() {
    OverviewScreen(darkTheme = false)
}
