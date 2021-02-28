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

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.GridPuppies
import com.example.androiddevchallenge.model.Puppy
import com.example.androiddevchallenge.ui.compose.AppStyle
import com.example.androiddevchallenge.ui.compose.ComposePreviewData
import com.example.androiddevchallenge.ui.compose.PREVIEW_DARK_THEME
import com.example.androiddevchallenge.ui.compose.PREVIEW_LIGHT_THEME
import com.example.androiddevchallenge.ui.compose.ScreenConfiguration
import com.example.androiddevchallenge.ui.compose.Size
import com.example.androiddevchallenge.ui.theme.AppTheme
import kotlin.math.ceil
import kotlin.math.floor

@Composable
fun PuppyList(
    navController: NavController,
    screenConfiguration: ScreenConfiguration,
    puppies: List<Puppy>
) {
    BoxWithConstraints {
        screenConfiguration.run {
            val puppyCardWidthDp = remember(orientation, maxWidth, maxHeight) {
                getPuppyCardWidthDp(
                    orientation, maxWidth.value.toInt(), maxHeight.value.toInt()
                )
            }
            val columns = remember(maxWidth, maxHeight) {
                floor(maxWidth.value.toInt() / puppyCardWidthDp.toFloat()).toInt()
            }

            LazyGridColumnPuppies(
                navController = navController,
                screenConfiguration = screenConfiguration,
                puppies = puppies,
                columns = columns,
                puppyCardWidthDp = puppyCardWidthDp
            )
        }
    }
}

fun getPuppyCardWidthDp(orientation: Int, screenWidthDp: Int, screenHeightDp: Int): Int {
    return if (orientation == Configuration.ORIENTATION_PORTRAIT) {
        floor(screenWidthDp / 2f).toInt()
    } else {
        val column = floor(screenWidthDp / ceil(screenHeightDp / 2f))
        floor(screenWidthDp / column).toInt()
    }
}

fun getPuppyCardCaptionHeightDp(
    spSize: Float,
    lineHeight: Float,
    maxLines: Int,
    density: Float,
    scaledDensity: Float
): Int {
    val fontSizeDp = Size.convertSpToDp(
        spSize = spSize,
        density = density,
        scaledDensity = scaledDensity
    )

    Log.v("Saya", ((fontSizeDp * maxLines) + (lineHeight * (maxLines - 1))).toInt().toString())
    return ((fontSizeDp * maxLines) + (lineHeight * (maxLines - 1))).toInt()
}

fun getGridPuppies(puppies: List<Puppy>, columns: Int): List<GridPuppies> {

    val gridPuppies = mutableListOf<GridPuppies>()
    val rowPuppies = mutableListOf<Puppy>()

    puppies.forEachIndexed { index, puppy ->
        if (index % columns == 0) {
            rowPuppies.clear()
        }
        rowPuppies.add(puppy)

        if (index % columns == (columns - 1) || index == puppies.size - 1) {
            gridPuppies.add(GridPuppies(rowPuppies.toList()))
        }
    }

    return gridPuppies
}

@Composable
fun LazyGridColumnPuppies(
    navController: NavController,
    modifier: Modifier = Modifier,
    screenConfiguration: ScreenConfiguration,
    puppies: List<Puppy>,
    columns: Int,
    puppyCardWidthDp: Int
) {

    val gridPuppies = remember(puppies, columns) {
        getGridPuppies(puppies, columns)
    }

    LazyColumn(modifier = modifier) {
        items(items = gridPuppies) { rowPuppies ->
            Row {
                rowPuppies.puppies.forEach { puppy ->
                    screenConfiguration.run {
                        PuppyCard(
                            puppyCardWidthDp = puppyCardWidthDp,
                            captionHeightDp = getPuppyCardCaptionHeightDp(
                                spSize = AppStyle.PuppyCard.Caption.fontStyle.fontSize.value,
                                lineHeight = AppStyle.PuppyCard.Caption.lineHeight,
                                maxLines = AppStyle.PuppyCard.Caption.maxLines,
                                density = density,
                                scaledDensity = scaledDensity
                            ),
                            puppy = puppy,
                            onClick = {
                                navController.navigate("detail/${puppy.id}")
                            }
                        )
                    }
                }
            }
            Column {
                Divider(
                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                    color = colorResource(id = R.color.divider)
                )
            }
        }
    }
}

@Composable
fun PuppyCard(
    puppyCardWidthDp: Int,
    captionHeightDp: Int,
    puppy: Puppy,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(puppyCardWidthDp.dp)
            .clickable(onClick = onClick),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(puppy.imageId),
            contentDescription = null,
            modifier = Modifier.size((puppyCardWidthDp - 20).dp)
        )
        Text(
            text = puppy.moreInformation ?: "",
            modifier = Modifier
                .height(captionHeightDp.dp)
                .width((puppyCardWidthDp - 20).dp),
            maxLines = AppStyle.PuppyCard.Caption.maxLines,
            overflow = TextOverflow.Ellipsis,
            style = AppStyle.PuppyCard.Caption.fontStyle
        )
    }
}

@Preview(PREVIEW_DARK_THEME)
@Composable
fun DarkThemePuppyCardPreview() {
    AppTheme(darkTheme = true) {
        PuppyCardPreview()
    }
}

@Preview(PREVIEW_LIGHT_THEME)
@Composable
fun LightThemePuppyCardPreview() {
    AppTheme(darkTheme = false) {
        PuppyCardPreview()
    }
}

@Composable
fun PuppyCardPreview() {
    ComposePreviewData.screenConfiguration.run {
        PuppyCard(
            puppyCardWidthDp = getPuppyCardWidthDp(orientation, screenWidthDp, screenHeightDp),
            captionHeightDp = getPuppyCardCaptionHeightDp(
                spSize = AppStyle.PuppyCard.Caption.fontStyle.fontSize.value,
                lineHeight = AppStyle.PuppyCard.Caption.lineHeight,
                maxLines = AppStyle.PuppyCard.Caption.maxLines,
                density = density,
                scaledDensity = scaledDensity
            ),
            puppy = ComposePreviewData.puppies[0],
            onClick = {}
        )
    }
}
