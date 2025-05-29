package com.desafio.animeapi.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Surface
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.desafio.animeapi.presentation.anime_list.AnimeListScreen
import com.desafio.animeapi.presentation.home.HomeScreen
import com.desafio.animeapi.presentation.login.LoginScreen
import com.desafio.animeapi.presentation.login.LoginViewModel
import com.desafio.animeapi.presentation.register.RegisterScreen
import com.desafio.animeapi.presentation.splash_screen.SplashScreenViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val splashScreenViewModel: SplashScreenViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                splashScreenViewModel.state.value.isLoading
            }
        }
        setContent {
            Surface {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.HomeScreen.route
                ) {
                    composable(
                        route = Screen.HomeScreen.route
                    ) {
                        HomeScreen(navController = navController)
                    }
                    composable(
                        route = Screen.LoginScreen.route
                    ) {
                        val loginViewModel: LoginViewModel = koinViewModel()
                        val launcher =
                            rememberLauncherForActivityResult(
                                contract = ActivityResultContracts.StartIntentSenderForResult(),
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
//                            onGoogleLoginClick = {
//                                lifecycleScope.launch {
//                                    val signInIntent = GoogleAuthUiClient.signIn(
//                                        Identity.getSignInClient(applicationContext)
//                                    )
//                                    launcher.launch(
//                                        IntentSenderRequest.Builder(
//                                            signInIntent ?: return@launch
//                                        ).build()
//                                    )
//                                }
                        )
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