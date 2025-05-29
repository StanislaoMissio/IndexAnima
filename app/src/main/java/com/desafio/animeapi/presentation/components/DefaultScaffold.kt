package com.desafio.animeapi.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.desafio.animeapi.R
import com.desafio.animeapi.theme.Red

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultScaffold(
    navController: NavController,
    content: @Composable BoxScope.() -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Red
                ),
                title = {
                    SearchField()
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.notifications),
                            contentDescription = "notifications",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        bottomBar = {
            CustomBottomBar(navController = navController)
        },
        containerColor = Red,

        ) {
        Box(modifier = Modifier.padding(it), content = content)
    }
}