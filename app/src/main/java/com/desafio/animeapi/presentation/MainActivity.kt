package com.desafio.animeapi.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.desafio.animeapi.presentation.anime_list.AnimeListScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.AnimeListScreen.route
                ) {
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