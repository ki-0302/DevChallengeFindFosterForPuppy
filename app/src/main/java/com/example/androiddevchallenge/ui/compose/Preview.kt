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

import android.content.res.Configuration
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.Gender
import com.example.androiddevchallenge.model.Puppy
import com.example.androiddevchallenge.ui.theme.typography

const val PREVIEW_DARK_THEME = "Dark THEME"
const val PREVIEW_LIGHT_THEME = "Light THEME"
const val PREVIEW_WIDTH = 360
const val PREVIEW_HEIGHT = 640
const val PREVIEW_DENSITY = 2.0f
const val PREVIEW_SCALED_DENSITY = 2.0f
const val PREVIEW_FONT_SCALE = 1.0f

class AppStyle {
    class PuppyCard {
        class Caption {
            companion object {
                const val maxLines = 2
                val fontStyle = typography.h5
                val lineHeight = 10f
            }
        }
    }
}

class ComposePreviewData {
    companion object {
        val screenConfiguration = ScreenConfiguration(
            orientation = Configuration.ORIENTATION_PORTRAIT,
            screenWidthDp = PREVIEW_WIDTH,
            screenHeightDp = PREVIEW_HEIGHT,
            density = PREVIEW_DENSITY,
            scaledDensity = PREVIEW_SCALED_DENSITY
        )

        val puppies: List<Puppy> = listOf(
            Puppy(
                name = "Taro",
                gender = Gender.MALE,
                birthDate = "2021/01/01",
                breed = "Akita",
                weight = 11f,
                moreInformation = "He is very active! He is so cool!",
                imageId = R.mipmap.puppy_taro
            ),
            Puppy(
                name = "Hanako",
                gender = Gender.FEMALE,
                birthDate = "2020/12/24",
                breed = "Kai",
                weight = 8f,
                moreInformation = "She is so cute.",
                imageId = R.mipmap.puppy_hanako
            ),
            Puppy(
                name = "Jiro",
                gender = Gender.MALE,
                birthDate = "2020/08/05",
                breed = "Kisyu",
                weight = 8f,
                moreInformation = "He is quiet and doesn't bark much.",
                imageId = R.mipmap.puppy_jiro
            ),
            Puppy(
                name = "Chacha",
                gender = Gender.FEMALE,
                birthDate = "2020/07/07",
                breed = "Dachshund",
                weight = 12f,
                moreInformation = "She is quiet and doesn't bark much.",
                imageId = R.mipmap.puppy_chacha
            ),
            Puppy(
                name = "Kotaro",
                gender = Gender.MALE,
                birthDate = "2021/02/02",
                breed = "Pomeranian",
                weight = 3f,
                moreInformation = "He is quiet and doesn't bark much.",
                imageId = R.mipmap.puppy_kotaro
            ),
            Puppy(
                name = "Momo",
                gender = Gender.MALE,
                birthDate = "2021/01/05",
                breed = "Tosa",
                weight = 12f,
                moreInformation = "She is quiet and doesn't bark much.",
                imageId = R.mipmap.puppy_momo
            )
        )
    }
}
