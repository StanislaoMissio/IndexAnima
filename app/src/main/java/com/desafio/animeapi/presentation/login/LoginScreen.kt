package com.desafio.animeapi.presentation.login

import android.content.IntentSender
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.desafio.animeapi.R
import com.desafio.animeapi.common.Constants
import com.desafio.animeapi.presentation.Screen
import com.desafio.animeapi.presentation.login.components.ErrorDialog
import com.desafio.animeapi.presentation.login.components.InvisibleBackground
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import java.util.*
import java.util.concurrent.CancellationException

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel,
    onGoogleLoginClick: () -> Unit
) {
    var email by remember { loginViewModel.email }
    var password by remember { loginViewModel.password }
    var rememberPassword by remember { mutableStateOf(false) }
    var passwordVisibility by remember { mutableStateOf(false) }
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
                placeholder = {
                    Text(
                        text = stringResource(
                            id = R.string.email_label
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
                ),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            passwordVisibility = !passwordVisibility
                        },
                        content = {
                            if (passwordVisibility) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.icon_visibility_on),
                                    contentDescription = null
                                )
                            } else {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.icon_visibility_off),
                                    contentDescription = null
                                )
                            }
                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = Color.White
                        )
                    )
                },
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation()
            )
            ClickableText(
                text = AnnotatedString(stringResource(id = R.string.forgot_password)),
                onClick = { /*TODO call forgot password screen*/ },
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp,
                    fontFamily = Font(R.font.roboto).toFontFamily()
                ),
                modifier = Modifier
                    .align(End)
                    .padding(top = 8.dp, end = 8.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
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
                onClick = { loginViewModel.doLoginWithEmailAndPassword() },
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
                    .padding(top = 18.dp, start = 96.dp, end = 96.dp)
            )
            Button(
                onClick = { onGoogleLoginClick() },
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
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(bottom = 24.dp)
                .align(BottomCenter)
        ) {
            Text(
                text = stringResource(id = R.string.register_label),
                textAlign = TextAlign.Center,
                color = Color.White
            )
            ClickableText(
                text = AnnotatedString(stringResource(id = R.string.register_underline_label)),
                modifier = Modifier.padding(start = 8.dp),
                onClick = { navController.navigate(Screen.RegisterScreen.route) },
                style = TextStyle(
                    color = Color.White,
                    textDecoration = TextDecoration.Underline,
                    textAlign = TextAlign.Center
                )
            )
        }

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
        navController.navigate(Screen.AnimeListScreen.route)
    }
}