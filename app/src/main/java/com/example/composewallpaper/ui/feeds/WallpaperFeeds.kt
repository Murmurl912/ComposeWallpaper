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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
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
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.composewallpaper.ui.support.BlurHashDecoder
import com.example.wallpaper.WallpaperCategory
import com.example.wallpaper.WallpaperPhoto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WallpaperFeeds(
    modifier: Modifier = Modifier,
    gridViewState: MutableState<Boolean> = remember {
        mutableStateOf(false)
    },
    categories: Flow<PagingData<WallpaperCategory>>,
    wallpapers: (WallpaperCategory) -> Flow<PagingData<WallpaperPhoto>>,
) {
    var gridView by remember {
        gridViewState
    }

    Column(modifier) {
        TopAppBar(title = {
            Text(text = "Wallpaper")
        },
            actions = {
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

        val categoryItems = categories.collectAsLazyPagingItems()
        when (val loadState = categoryItems.loadState.refresh) {
            LoadState.Loading -> {
                WallpaperFeedLoadingContent(
                    Modifier
                        .weight(1F)
                        .fillMaxWidth()
                )
            }
            is LoadState.NotLoading -> {
                WallpaperFeedContent(
                    Modifier
                        .weight(1F)
                        .fillMaxWidth(),

                    gridViewState.value,
                    categoryItems,
                    wallpapers
                ) {

                }
            }
            is LoadState.Error -> {
                WallpaperFeedLoadFailureContent(
                    Modifier
                        .weight(1F)
                        .fillMaxWidth(),
                    loadState.error.message?:""
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
    categories: LazyPagingItems<WallpaperCategory>,
    wallpapers: (WallpaperCategory) -> Flow<PagingData<WallpaperPhoto>>,
    onSelect: (WallpaperCategory) -> Unit
) {


    Column(modifier) {
        LazyRow {
            items(count = categories.itemCount) {
                val category = categories[it]
                if (category != null) {
                    Tab(selected = false, onClick = {

                    }) {
                        Text(text = category.title(), Modifier.padding(horizontal = 12.dp))
                    }
                }

            }
        }

        HorizontalPager(categories.itemCount, modifier = Modifier
            .fillMaxWidth()
            .weight(1F)) {
            val category = categories[it]
            if (category != null) {
                val wallpapers = wallpapers(category).collectAsLazyPagingItems()
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
                    items(wallpapers.itemCount) {
                        val wallpaper = wallpapers[it]
                        if (wallpaper != null) {
                            var itemSize by remember {
                                mutableStateOf(IntSize.Zero)
                            }
                            val colorPainter = remember {
                                ColorPainter(Color(wallpaper.color()))
                            }
                            val blurPainter: Painter? = remember(itemSize) {
                                val bitmap = kotlin.runCatching {
                                    BlurHashDecoder.decode(
                                        wallpaper.blur(),
                                        itemSize.width,
                                        itemSize.height
                                    )
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
    }

}

@Composable
fun WallpaperFeedLoadingContent(modifier: Modifier = Modifier) {
    Box(modifier) {
        CircularProgressIndicator(Modifier.align(Alignment.Center))
    }
}

@Composable
fun WallpaperFeedLoadFailureContent(modifier: Modifier = Modifier, message: String) {
    Column(modifier, verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = message)
        Button(onClick = { /*TODO*/ }) {
            Text("Retry")
        }
    }
}


@Preview
@Composable
fun WallpaperFeedPreview() {
    WallpaperFeeds(
        Modifier.fillMaxSize(),
        categories = emptyFlow(),
        wallpapers = {
        emptyFlow()
        }
    )
}