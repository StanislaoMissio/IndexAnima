package com.desafio.animeapi.presentation.anime_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.desafio.animeapi.domain.model.Anime

@Composable
fun AnimeListItem(
    anime: Anime,
    onItemClick: (Anime) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(anime) }
            .padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        anime.attributes.posterImage?.let {
            AsyncImage(
                model = it.small,
                contentDescription = null,
                modifier = Modifier
                    .clip(RectangleShape)
                    .padding(end = 15.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = anime.attributes.titles.en_jp,
                style = MaterialTheme.typography.bodySmall,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(5.dp)
            )
        }
    }
}