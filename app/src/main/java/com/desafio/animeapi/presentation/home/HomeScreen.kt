package com.desafio.animeapi.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.desafio.animeapi.presentation.components.DefaultScaffold
import com.desafio.animeapi.presentation.home.components.TopAnimeCarouselItem
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = koinViewModel()
) {
    val state = viewModel.state.value

    DefaultScaffold(
        navController = navController
    ) {
        Box(modifier = Modifier
            .fillMaxSize()) {
            HorizontalPager(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(start = 16.dp, end = 16.dp),
                state = CustomPagerState(state.topAnime.size),
                pageSize = PageSize.Fixed(150.dp),
                pageSpacing = 16.dp
            ) { key ->
                TopAnimeCarouselItem(
                    modifier = Modifier
                        .background(color = Color.Transparent),
                    item = state.topAnime[key]
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
class CustomPagerState(override val pageCount: Int) : PagerState()