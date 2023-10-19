package com.desafio.animeapi.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.desafio.animeapi.common.GoogleAuthUiClient
import com.desafio.animeapi.presentation.anime_list.AnimeListScreen
import com.desafio.animeapi.presentation.login.LoginScreen
import com.desafio.animeapi.presentation.login.LoginViewModel
import com.desafio.animeapi.presentation.register.RegisterScreen
import com.desafio.animeapi.presentation.splash_screen.SplashScreen
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

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
                        val loginViewModel: LoginViewModel = koinViewModel()
                        val state = loginViewModel.state.value
                        val launcher =
                            rememberLauncherForActivityResult(contract = ActivityResultContracts.StartIntentSenderForResult(),
                                onResult = { result ->
                                    if (result.resultCode == RESULT_OK) {
                                        lifecycleScope.launch {
                                            loginViewModel.doLoginWithGoogle(
                                                intent = result.data ?: return@launch
                                            )
                                        }
                                    }

                                })

                        LoginScreen(
                            navController = navController,
                            loginViewModel = loginViewModel,
                            onGoogleLoginClick = {
                                lifecycleScope.launch {
                                    val signInIntent = GoogleAuthUiClient.signIn(
                                        Identity.getSignInClient(applicationContext)
                                    )
                                    launcher.launch(
                                        IntentSenderRequest.Builder(
                                            signInIntent ?: return@launch
                                        ).build()
                                    )
                                }
                            })
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