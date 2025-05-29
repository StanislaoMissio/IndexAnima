package com.desafio.animeapi.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.desafio.animeapi.R
import com.desafio.animeapi.presentation.Screen
import com.desafio.animeapi.presentation.components.ErrorDialog
import com.desafio.animeapi.theme.Red
import org.koin.androidx.compose.koinViewModel

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun LoginScreen(
    navController: NavController = rememberNavController(),
    loginViewModel: LoginViewModel = koinViewModel()
) {

    var email by remember { loginViewModel.email }
    var password by remember { loginViewModel.password }
    var rememberPassword by remember { mutableStateOf(false) }
    var passwordVisibility by remember { mutableStateOf(false) }

    val state = loginViewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        Icon(
            painter = painterResource(R.drawable.google_icon),
            tint = Red,
            contentDescription = "logo"
        )
        Text(
            text = "IndexAnima"
        )

    }

    if (state.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Center)
            )
        }
    }
    if (state.error.isNotBlank()) {
        ErrorDialog(
            onDismissRequest = { loginViewModel.cleanErrorState() },
            confirmButtonText = stringResource(id = R.string.ok),
            title = stringResource(id = R.string.connection_error),
            substring = state.error
        )
    }
    if (state.login) {
        navController.navigate(Screen.HomeScreen.route)
    }
}