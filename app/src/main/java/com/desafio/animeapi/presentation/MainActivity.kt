package com.desafio.animeapi.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.desafio.animeapi.presentation.anime_list.AnimeListScreen
import com.desafio.animeapi.presentation.login.LoginScreen
import com.desafio.animeapi.presentation.register.RegisterScreen
import com.desafio.animeapi.presentation.splash_screen.SplashScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.SplashScreen.route
                ) {
                    composable(
                        route = Screen.SplashScreen.route
                    ) {
                        SplashScreen(navController = navController)
                    }
                    composable(
                        route = Screen.LoginScreen.route
                    ) {
                        LoginScreen(navController = navController)
                    }
                    composable(
                        route = Screen.RegisterScreen.route
                    ) {
                        RegisterScreen(navController = navController)
                    }
                    composable(
                        route = Screen.AnimeListScreen.route
                    ) {
                        AnimeListScreen(navController = navController)
                    }
                    composable(
                        route = Screen.AnimeDetailScreen.route
                    ) {
                        //TODO
                    }
                }
            }
        }
    }
}