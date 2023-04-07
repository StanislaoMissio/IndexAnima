package com.desafio.animeapi.presentation.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import com.desafio.animeapi.R
import com.desafio.animeapi.presentation.Screen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = koinViewModel()
) {
    var email by remember { loginViewModel.email }
    var password by remember { loginViewModel.password }
    val state = loginViewModel.state.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp)
    ) {
        OutlinedTextField(value = email, onValueChange = { email = it }, label = {
            Text(
                text = stringResource(
                    id = R.string.email_label
                )
            )
        })
        OutlinedTextField(value = password, onValueChange = { password = it }, label = {
            Text(
                text = stringResource(
                    id = R.string.password_label
                )
            )
        })
        Button(onClick = { loginViewModel.doLogin() }, content = { Text(text = "Logar") })
        Text(
            text = stringResource(id = R.string.register_label),
            modifier = Modifier.clickable(
                enabled = true,
                onClick = { navController.navigate(Screen.RegisterScreen.route) }
            )
        )
    }

    if (state.isLoading) {
        CircularProgressIndicator()
    }
    if (state.error.isNotBlank()) {
        Text(text = state.error)
    }
    if (state.login) {
        navController.navigate(Screen.AnimeListScreen.route)
    }
}