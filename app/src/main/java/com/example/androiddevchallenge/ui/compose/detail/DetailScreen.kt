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
package com.example.androiddevchallenge.ui.compose.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.Puppy
import com.example.androiddevchallenge.ui.compose.AppStyle
import com.example.androiddevchallenge.ui.compose.ComposePreviewData
import com.example.androiddevchallenge.ui.compose.PREVIEW_DARK_THEME
import com.example.androiddevchallenge.ui.compose.PREVIEW_HEIGHT
import com.example.androiddevchallenge.ui.compose.PREVIEW_LIGHT_THEME
import com.example.androiddevchallenge.ui.compose.PREVIEW_WIDTH
import com.example.androiddevchallenge.ui.compose.TopAppBar
import com.example.androiddevchallenge.ui.theme.AppTheme

@Composable
fun DetailScreen(
    navController: NavController,
    darkTheme: Boolean = isSystemInDarkTheme(),
    id: Int
) {

    val puppy = remember(id) { getPuppyFromId(id) }

    AppTheme(darkTheme = darkTheme) {
        Scaffold(
            topBar = { TopAppBar() },
            content = {
                if (puppy == null) {
                    Text("No Data")
                } else {
                    PuppyInformation(puppy!!)
                }
            }
        )
    }
}

fun getPuppyFromId(id: Int): Puppy? {
    ComposePreviewData.puppies.forEach { puppy ->
        if (puppy.id == id) return puppy
    }
    return null
}

@Composable
fun PuppyInformation(
    puppy: Puppy
) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = painterResource(puppy.imageId),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
        puppy.run {
            PuppyInformationNameColumn(R.string.puppy_caption_name, name)
            PuppyInformationColumn(R.string.puppy_caption_gender, gender?.name)
            PuppyInformationColumn(R.string.puppy_caption_birth_date, birthDate)
            PuppyInformationColumn(R.string.puppy_caption_breed, breed)
            PuppyInformationColumn(R.string.puppy_caption_weight, weight?.toString())
        }
    }
}

@Composable
fun PuppyInformationCaption(
    caption: String,
    style: TextStyle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        letterSpacing = 0.15.sp
    )
) {
    Text(
        text = caption,
        style = style
    )
}

@Composable
fun PuppyInformationDescription(description: String, style: TextStyle = typography.body2) {
    Text(
        text = description,
        style = style
    )
}

@Composable
fun PuppyInformationColumn(
    captionId: Int,
    description: String?
) {
    if (description == null) return
    Column {
        PuppyInformationCaption(stringResource(id = captionId))
        PuppyInformationDescription(description)
        Divider(
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
            color = colorResource(id = R.color.divider)
        )
    }
}

@Composable
fun PuppyInformationNameColumn(
    captionId: Int,
    description: String?
) {
    if (description == null) return
    Column {
        Divider(
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
            color = colorResource(id = R.color.divider)
        )
        PuppyInformationCaption(
            stringResource(id = captionId),
            AppStyle.PuppyCard.Caption.fontStyle
        )
        PuppyInformationDescription(description, typography.body1)
        Divider(
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
            color = colorResource(id = R.color.divider)
        )
    }
}

@Preview(PREVIEW_DARK_THEME, widthDp = PREVIEW_WIDTH, heightDp = PREVIEW_HEIGHT)
@Composable
fun DarkThemeDetailScreenPreview() {
    DetailScreenPreview(darkTheme = true)
}

@Preview(PREVIEW_LIGHT_THEME, widthDp = PREVIEW_WIDTH, heightDp = PREVIEW_HEIGHT)
@Composable
fun LightThemeDetailScreenPreview() {
    DetailScreenPreview(darkTheme = false)
}

@Composable
fun DetailScreenPreview(darkTheme: Boolean) {
    DetailScreen(
        navController = rememberNavController(),
        darkTheme = darkTheme,
        id = 1
    )
}
