package com.desafio.animeapi.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.desafio.animeapi.R

@Composable
@Preview
fun ErrorDialog(
    onDismissRequest: () -> Unit = {},
    confirmButtonText: String = "OK",
    title: String = "Erro de conexão",
    substring: String = "Tente novamente mais tarde."
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            Button(
                onClick = onDismissRequest,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = colorResource(id = R.color.button_text_color)
                )
            ) {
                Text(text = confirmButtonText)
            }
        },
        title = {
            Text(
                text = title,
                color = colorResource(id = R.color.titleColor)
            )
        },
        text = {
            Text(
                text = substring,
                color = colorResource(id = R.color.substringColor)
            )
        },
        containerColor = colorResource(id = R.color.ECE6F0)
    )
}