package com.compose.waterfilter.ui.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.waterfilter.ui.component.ringindicator.Ring
import com.compose.waterfilter.ui.theme.ColorRingBackground
import com.compose.waterfilter.ui.theme.ColorRingForeground
import com.compose.waterfilter.ui.theme.WaterFilterTheme

@Composable
fun MainScreen(mainViewModel: MainViewModel) {
    WaterFilterTheme() {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            MyScreenContent()

        }
    }
}

@Composable
fun MyScreenContent() {

    Ring(bgColor = MaterialTheme.colors.ColorRingBackground,
        fgColor = MaterialTheme.colors.ColorRingForeground,
        fill = .5f)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyScreenContent()
}