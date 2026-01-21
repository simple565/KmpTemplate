package com.kmp.template.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.elife.room.ui.theme.AppTheme
import com.kmp.template.ui.home.HomePage

@Composable
fun App() {
    AppTheme {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = HomePage) {
            composable<HomePage> {
                HomePage(
                    navController = navController,
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable<DetailPage> {
                DetailPage(
                    id = it.toRoute<DetailPage>().id,
                    onBackClick = navController::popBackStack,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}