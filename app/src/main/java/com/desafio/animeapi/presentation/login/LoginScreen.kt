package com.desafio.animeapi.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.desafio.animeapi.R
import com.desafio.animeapi.presentation.Screen
import com.desafio.animeapi.presentation.login.components.InvisibleBackground
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = koinViewModel()
) {
    var email by remember { loginViewModel.email }
    var password by remember { loginViewModel.password }
    var rememberPassword by remember { mutableStateOf(false) }
    val state = loginViewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        InvisibleBackground(R.drawable.login_background)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            horizontalAlignment = CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.login_label),
                fontStyle = FontStyle.Normal,
                fontSize = 36.sp,
                fontFamily = Font(R.font.century_gothic).toFontFamily(),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 150.dp, start = 24.dp, end = 24.dp),
                color = colorResource(id = R.color.C7C7C7)
            )
            TextField(
                value = email,
                onValueChange = { email = it },
                label = {
                    Text(
                        text = stringResource(
                            id = R.string.email_label
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 54.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.White,
                    focusedIndicatorColor = Color.White,
                    textColor = Color.Gray
                ),
            )
            TextField(
                value = password,
                onValueChange = { password = it },
                label = {
                    Text(
                        text = stringResource(
                            id = R.string.password_label
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.White,
                    focusedIndicatorColor = Color.White,
                    textColor = Color.Gray
                )
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                verticalAlignment = CenterVertically
            ) {
                Checkbox(
                    checked = rememberPassword,
                    onCheckedChange = { rememberPassword = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color.White,
                        uncheckedColor = Color.White
                    )
                )
                Text(
                    text = stringResource(id = R.string.remember_password),
                    color = colorResource(id = R.color.C7C7C7)
                )
            }
            Button(
                onClick = { loginViewModel.doLogin() },
                content = {
                    Text(
                        text = stringResource(id = R.string.login),
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.AF3BCD),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 96.dp, end = 96.dp)
            )
            Button(
                onClick = { loginViewModel.doLogin() },
                content = {
                    Row(verticalAlignment = CenterVertically) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.google_icon),
                            contentDescription = null,
                            modifier = Modifier.padding(end = 16.dp)
                        )
                        Text(
                            text = stringResource(id = R.string.login_with_google),
                            fontWeight = FontWeight.Bold
                        )
                    }

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 46.dp, end = 46.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(BottomCenter)
        ) {
            Text(
                text = stringResource(id = R.string.register_label),
                textAlign = TextAlign.Center,
                color = Color.White
            )
            Text(
                text = stringResource(id = R.string.register_underline_label),
                textAlign = TextAlign.Center,
                color = Color.White,
                textDecoration = TextDecoration.Underline
            )
        }

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