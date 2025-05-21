package com.desafio.animeapi.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.desafio.animeapi.R

@Composable
@Preview
fun DefaultButton(
    onClick: () -> Unit = {},
    content: Int = R.string.register_underline_label
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.AF3BCD),
            contentColor = colorResource(id = R.color.white)
        ),
        content = {
            Text(
                text = stringResource(id = content).uppercase(),
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
            )
        },
        modifier = Modifier.padding(start = 80.dp, end = 80.dp).fillMaxWidth()
    )
}