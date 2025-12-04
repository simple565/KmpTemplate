package com.kmp.template.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.elife.room.ui.theme.AppTheme

@Composable
fun App() {
    AppTheme {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = ListPage) {
            composable<ListPage> {
                ListPage(
                    onDetailClick = { navController.navigate(route = DetailPage(it)) },
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable<DetailPage> {
                DetailPage(
                    detail = it.toRoute<DetailPage>().value,
                    onBackClick = navController::popBackStack,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}