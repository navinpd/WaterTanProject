package com.compose.waterfilter.ui.screen.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.compose.waterfilter.PlaceHolderDependency
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Address:
 * https://www.youtube.com/playlist?list=PLeYPyxTMnOLW7O_AZWLWHgeMIMMBYOZLu
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var placeHolderDependency: PlaceHolderDependency

    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(viewModel)
        }
    }
}