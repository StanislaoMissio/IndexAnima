package com.desafio.animeapi.presentation.splash_screen

import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.desafio.animeapi.presentation.Screen
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    navController: NavController,
    splashScreenViewModel: SplashScreenViewModel = koinViewModel()
) {
    val state = splashScreenViewModel.state.value

    if (state.isLoading) {
        LinearProgressIndicator()
    }
    if (state.user != null) {
        navController.navigate(Screen.HomeScreen.route)
    } else {
        navController.navigate(Screen.LoginScreen.route)
    }

}