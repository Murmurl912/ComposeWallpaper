package com.example.composewallpaper

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Wallpaper
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Preview
@Composable
fun WallpaperDesign() {

    var isGridView by remember {
        mutableStateOf(false)
    }
    var currentTab by remember {
        mutableStateOf(0)
    }
    var selectedTab by remember {
        mutableStateOf(0)
    }
    Scaffold(
        Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = {
                Text(text = "Wallpaper")
            }, actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Default.Search, null)
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Default.FilterAlt, null)
                }
                if (isGridView) {
                    IconButton(onClick = {
                        isGridView = false
                    }) {
                        Icon(Icons.Default.ListAlt, null)
                    }
                } else {
                    IconButton(onClick = {
                        isGridView = true
                    }) {
                        Icon(Icons.Default.GridView, null)
                    }
                }
            })
        },
        bottomBar = {
            BottomAppBar {
                NavigationBarItem(selected = currentTab == 0, onClick = {
                    currentTab = 0
                }, icon = {
                    Icon(Icons.Default.Wallpaper, null)
                }, label = {
                    Text(text = "Feeds")
                })

                NavigationBarItem(selected = currentTab == 1, onClick = {
                    currentTab = 1
                }, icon = {
                    Icon(Icons.Default.History, null)
                }, label = {
                    Text(text = "Feeds")
                })

            }
        }
    ) {
        Column(Modifier.padding(it)) {

            ScrollableTabRow(selectedTabIndex = selectedTab, Modifier.fillMaxWidth()) {
                Tab(selected = true, onClick = { /*TODO*/ }) {
                    Text(text = "Wallpaper")
                }
                Tab(selected = true, onClick = { /*TODO*/ }) {
                    Text(text = "Blue")
                }
                Tab(selected = true, onClick = { /*TODO*/ }) {
                    Text(text = "Travel")
                }
                Tab(selected = true, onClick = { /*TODO*/ }) {
                    Text(text = "Fashion")
                }
                Tab(selected = true, onClick = { /*TODO*/ }) {
                    Text(text = "Beauty")
                }
            }
            HorizontalPager(12, modifier = Modifier
                .fillMaxWidth()
                .weight(1F)) {
                LazyVerticalStaggeredGrid(
                    columns = if (isGridView) {
                        StaggeredGridCells.Fixed(2)
                    } else {
                        StaggeredGridCells.Fixed(1)
                    },
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalItemSpacing = 12.dp,
                    contentPadding = PaddingValues(12.dp)
                ) {
                    item {
                        Image(painter = painterResource(id = R.drawable.wallpaper_tree_lake),
                            null, Modifier.clip(
                                RoundedCornerShape(12.dp)
                            ))
                    }
                    item {
                        Image(painter = painterResource(id = R.drawable.wallpaper_moon),
                            null, Modifier.clip(
                                RoundedCornerShape(12.dp))
                        )
                    }
                    item {
                        Image(painter = painterResource(id = R.drawable.wallpaper_snow_tree),
                            null, Modifier.clip(
                                RoundedCornerShape(12.dp))
                        )
                    }
                    item {
                        Image(painter = painterResource(id = R.drawable.wallpaper_bird),
                            null, Modifier.clip(
                                RoundedCornerShape(12.dp))
                        )
                    }
                }
            }
        }
    }

}