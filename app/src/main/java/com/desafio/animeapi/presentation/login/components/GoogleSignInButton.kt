package com.desafio.animeapi.presentation.login.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.desafio.animeapi.R
import com.desafio.animeapi.theme.Red

@Composable
@Preview
fun GoogleSignInButton(
    text: String = "Continue with Google",
    onClicked: () -> Unit = {}
) {
    TextButton(
        onClick = onClicked,
        modifier = Modifier
            .background(Color.White)
            .border(
                border = BorderStroke(width = 1.dp, color = Red),
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.google_icon),
            tint = Red,
            contentDescription = "Google Icon",
            modifier = Modifier.padding(end = 10.dp)
        )
        Text(
            text = text,
            color = Red,
            fontSize = 14.sp
        )
    }
}