package com.compose.waterfilter.ui.screen.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.waterfilter.ui.component.ringindicator.RingIndicator
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
    RingIndicator(
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth(),
        fill = 0.8f,
        1
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyScreenContent()
}