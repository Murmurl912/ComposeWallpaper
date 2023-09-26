package com.example.composewallpaper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.composewallpaper.ui.feeds.FeedsViewModel
import com.example.composewallpaper.ui.feeds.WallpaperFeeds
import com.example.composewallpaper.ui.theme.ComposeWallpaperTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeWallpaperTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: FeedsViewModel by viewModels()
                    WallpaperFeeds(
                        Modifier.fillMaxSize(),
                        remember {
                            mutableStateOf(false)
                        },
                        viewModel.feedsState.collectAsState().value,
                        {
                            viewModel.select(it)
                        }
                    )
                }
            }
        }
    }
}