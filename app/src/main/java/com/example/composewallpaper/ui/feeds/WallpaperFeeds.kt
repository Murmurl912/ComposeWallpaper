package com.example.composewallpaper.ui.feeds

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize

import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.composewallpaper.ui.support.BlurHashDecoder
import com.example.wallpaper.WallpaperCategory
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WallpaperFeeds(
    modifier: Modifier = Modifier,
    gridViewState: MutableState<Boolean> = remember {
        mutableStateOf(false)
    },
    state: WallpaperFeedState = remember {
        WallpaperFeedState.Loading
    },
    onSelect: (WallpaperCategory) -> Unit = {

    }
) {
    var gridView by remember {
        gridViewState
    }

    Column(modifier) {
        TopAppBar(title = {
            Text(text = "Wallpaper")
        }, actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Search, null)
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.FilterAlt, null)
            }
            if (gridView) {
                IconButton(onClick = {
                    gridView = false
                }) {
                    Icon(Icons.Default.ListAlt, null)
                }
            } else {
                IconButton(onClick = {
                    gridView = true
                }) {
                    Icon(Icons.Default.GridView, null)
                }
            }
        })

        when (state) {
            is WallpaperFeedState.Failure -> {
                WallpaperFeedLoadFailureContent(
                    Modifier
                        .weight(1F)
                        .fillMaxWidth(),
                    state
                )
            }
            is WallpaperFeedState.Loaded -> {
                WallpaperFeedContent(
                    Modifier
                        .weight(1F)
                        .fillMaxWidth(),

                    gridViewState.value, state, onSelect)
            }

            WallpaperFeedState.Loading -> {
                WallpaperFeedLoadingContent(
                    Modifier
                        .weight(1F)
                        .fillMaxWidth()
                )
            }
        }

    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WallpaperFeedContent(
    modifier: Modifier = Modifier,
    gridView: Boolean,
    feed: WallpaperFeedState.Loaded,
    onSelect: (WallpaperCategory) -> Unit
) {
    val index = remember(feed) {
        feed.categoryState.categories.indexOf(feed.categoryState.category)
    }

    Column(modifier) {

        val pagerState = rememberPagerState()

        val scope = rememberCoroutineScope()
        ScrollableTabRow(selectedTabIndex = index, Modifier.fillMaxWidth()) {
            for (category in feed.categoryState.categories) {
                Tab(selected = category == feed.categoryState.category, onClick = {
                    onSelect(category)
                }) {
                    Text(text = category.title(), Modifier.padding(horizontal = 12.dp))
                }
            }
        }

        HorizontalPager(feed.categoryState.categories.size, modifier = Modifier
            .fillMaxWidth()
            .weight(1F)) {
            LazyVerticalStaggeredGrid(
                columns = if (gridView) {
                    StaggeredGridCells.Fixed(2)
                } else {
                    StaggeredGridCells.Fixed(1)
                },
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalItemSpacing = 12.dp,
                contentPadding = PaddingValues(12.dp)
            ) {

                items(feed.wallpaperState.wallpapers.size, {
                    feed.wallpaperState.wallpapers[it].id()
                }) {
                    val wallpaper = feed.wallpaperState.wallpapers[it]
                    var itemSize by remember {
                        mutableStateOf(IntSize.Zero)
                    }
                    val colorPainter = remember {
                        ColorPainter(Color(wallpaper.color()))
                    }
                    val blurPainter: Painter? = remember(itemSize) {
                        val bitmap = kotlin.runCatching {
                            BlurHashDecoder.decode(wallpaper.blur(), itemSize.width, itemSize.height)
                        }.getOrNull()
                        if (bitmap == null) {
                            null
                        } else {
                            BitmapPainter(
                                bitmap.asImageBitmap()
                            )
                        }
                    }
                    AsyncImage(
                        model = wallpaper.thumbnail(),
                        contentDescription = wallpaper.description(),
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .aspectRatio(
                                wallpaper.ratio()
                            )
                            .onSizeChanged {
                                itemSize = it
                            },
                        placeholder = blurPainter ?: colorPainter,
                    )
                }
            }
        }
    }

}

@Composable
fun WallpaperFeedLoadingContent(modifier: Modifier = Modifier) {
    Box(modifier) {
        CircularProgressIndicator(Modifier.align(Alignment.Center))
    }
}

@Composable
fun WallpaperFeedLoadFailureContent(modifier: Modifier = Modifier, state: WallpaperFeedState.Failure) {
    Column(modifier, verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = state.message)
        TextButton(onClick = { /*TODO*/ }) {
            Text("Retry")
        }
    }
}


@Preview
@Composable
fun WallpaperFeedPreview() {
    WallpaperFeeds(
        Modifier.fillMaxSize()
    )
}