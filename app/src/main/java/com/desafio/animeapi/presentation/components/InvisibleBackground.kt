package com.desafio.animeapi.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.desafio.animeapi.R

@Composable
@Preview
fun InvisibleBackground(
    image: Int = R.drawable.ic_launcher_background
) {
    Image(
        painter = painterResource(id = image),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
            .blur(15.dp)
    )
}