package com.desafio.animeapi.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.desafio.animeapi.R
import com.desafio.animeapi.theme.PacificCyan
import java.util.Locale

@Composable
@Preview
fun SearchField(
    onSearchClick: () -> Unit = {},
    onFilterClick: () -> Unit = {}
) {

    Box(
        modifier = Modifier
            .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
            .background(Color.Transparent)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.CenterStart)
                .clip(RoundedCornerShape(50))
                .background(color = PacificCyan),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(modifier = Modifier.padding(start = 16.dp).wrapContentSize(), onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.page_info),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .height(18.dp)
                        .width(18.dp)
                )
            }
            Text(
                modifier = Modifier.align(CenterVertically),
                text = "Pesquisar Anime".uppercase(),
                fontStyle = FontStyle.Normal,
                color = Color.White,
                fontSize = 12.sp
            )
            IconButton(modifier = Modifier.padding(end = 16.dp), onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .height(18.dp)
                        .width(18.dp)
                )
            }
        }
    }

}