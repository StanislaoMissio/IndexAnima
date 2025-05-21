package com.desafio.animeapi.presentation.register

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.desafio.animeapi.R
import com.desafio.animeapi.presentation.Screen
import com.desafio.animeapi.presentation.components.DefaultButton
import com.desafio.animeapi.presentation.components.ErrorDialog
import com.desafio.animeapi.presentation.components.InvisibleBackground
import org.koin.androidx.compose.koinViewModel
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavController,
    registerViewModel: RegisterViewModel = koinViewModel()
) {

    var username by remember { registerViewModel.username }
    var email by remember { registerViewModel.email }
    var password by remember { registerViewModel.password }
    var repeatPassword by remember { registerViewModel.repeatPassword }
    var acceptedTerms by remember { registerViewModel.acceptedTerms }
    val state = registerViewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        InvisibleBackground(R.drawable.login_background)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.register_screen_title),
                fontStyle = FontStyle.Normal,
                fontSize = 36.sp,
                fontFamily = Font(R.font.century_gothic).toFontFamily(),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 150.dp, start = 24.dp, end = 24.dp),
                color = colorResource(id = R.color.C7C7C7)
            )
            TextField(
                value = username,
                onValueChange = { username = it },
                placeholder = {
                    Text(
                        text = stringResource(
                            id = R.string.username_label
                        ).uppercase(Locale.ROOT)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 54.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.White,
                    focusedIndicatorColor = colorResource(id = R.color.D195E0),
                    cursorColor = Color.White,
                    textColor = Color.White
                )
            )
            TextField(
                value = email,
                onValueChange = { email = it },
                placeholder = {
                    Text(
                        text = stringResource(
                            id = R.string.email_label
                        ).uppercase(Locale.ROOT)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.White,
                    focusedIndicatorColor = colorResource(id = R.color.D195E0),
                    cursorColor = Color.White,
                    textColor = Color.White
                )
            )
            TextField(
                value = password,
                onValueChange = { password = it },
                placeholder = {
                    Text(
                        text = stringResource(
                            id = R.string.password_label
                        ).uppercase(Locale.ROOT)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.White,
                    focusedIndicatorColor = colorResource(id = R.color.D195E0),
                    cursorColor = Color.White,
                    textColor = Color.White
                )
            )
            TextField(
                value = repeatPassword,
                onValueChange = { repeatPassword = it },
                placeholder = {
                    Text(
                        text = stringResource(
                            id = R.string.repeat_password_label
                        ).uppercase(Locale.ROOT)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.White,
                    focusedIndicatorColor = colorResource(id = R.color.D195E0),
                    cursorColor = Color.White,
                    textColor = Color.White
                )
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                verticalAlignment = CenterVertically,
            ) {
                Checkbox(
                    checked = acceptedTerms,
                    onCheckedChange = { acceptedTerms = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color.White,
                        uncheckedColor = Color.White
                    )
                )
                Text(
                    text = stringResource(id = R.string.accept_terms_label),
                    color = colorResource(id = R.color.C7C7C7)
                )
            }
            DefaultButton(
                onClick = { registerViewModel.doRegister() },
                content = R.string.register_underline_label
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(bottom = 24.dp)
                .align(BottomCenter)
        ) {
            Text(
                text = stringResource(id = R.string.return_login_label),
                textAlign = TextAlign.Center,
                color = Color.White
            )
            ClickableText(
                text = AnnotatedString(stringResource(id = R.string.login_underline_label)),
                modifier = Modifier.padding(start = 8.dp),
                onClick = { navController.popBackStack() },
                style = TextStyle(
                    color = Color.White,
                    textDecoration = TextDecoration.Underline,
                    textAlign = TextAlign.Center
                )
            )
        }
    }

    if (state.isLoading) {
        Row(horizontalArrangement = Arrangement.Center) {
            LinearProgressIndicator(color = colorResource(id = R.color.D195E0))
        }
    }

    if (state.error.isNotEmpty()) {
        ErrorDialog(
            onDismissRequest = { registerViewModel.cleanErrorState() },
            confirmButtonText = stringResource(id = R.string.ok),
            title = stringResource(id = R.string.connection_error),
            substring = state.error
        )
    }

    if (state.registered) {
        navController.navigate(Screen.AnimeListScreen.route)
    }
}