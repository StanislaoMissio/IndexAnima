package com.desafio.animeapi.presentation.home.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.desafio.animeapi.domain.model.Anime

@Composable
fun TopAnimeCarouselItem(
    modifier: Modifier,
    item: Anime
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(10),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
            contentColor = Color.Transparent
        )
    ) {
        AsyncImage(
            model = item.attributes.posterImage?.original,
            contentDescription = "Cover Image",
            modifier = Modifier
                .height(250.dp)
                .width(170.dp),
            contentScale = ContentScale.FillBounds
        )
    }
}