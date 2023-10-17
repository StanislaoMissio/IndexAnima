package com.desafio.animeapi.presentation.home.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.desafio.animeapi.domain.model.Anime

@Composable
fun TopAnimeCarouselItem(
    modifier: Modifier,
    item: Anime
) {
    Card(
        modifier = modifier
    ) {
        AsyncImage(
            model = item.attributes.posterImage?.original,
            contentDescription = "Cover Image",
            modifier = Modifier
                .clip(RoundedCornerShape(20)),
            contentScale = ContentScale.FillHeight
        )
    }
}