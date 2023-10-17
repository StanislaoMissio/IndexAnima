package com.desafio.animeapi.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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

    DefaultScaffold(navController = navController) {
        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .align(Alignment.TopCenter),
            state = CustomPagerState(state.topAnime.size),
            pageSize = PageSize.Fixed(300.dp),
            contentPadding = PaddingValues(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) { key ->
            TopAnimeCarouselItem(
                modifier = Modifier
                    .background(color = Color.Transparent)
                    .align(Alignment.Center),
                item = state.topAnime[key]
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
class CustomPagerState(override val pageCount: Int) : PagerState()