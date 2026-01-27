package com.kmp.template.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.kmp.template.ui.DetailPage
import com.kmp.template.ui.ListPage
import com.kmp.template.ui.settings.SettingsPage
import kotlinx.serialization.Serializable

@Serializable
object HomePage

@Composable
fun HomePage(navController: NavHostController, modifier: Modifier = Modifier) {
    var selectedItem by rememberSaveable { mutableStateOf(0) }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            item(
                selected = selectedItem == 0,
                onClick = { selectedItem = 0 },
                icon = {
                    Icon(imageVector = Icons.Default.Home, contentDescription = null)
                },
                label = { Text("Home") }
            )

            item(
                selected = selectedItem == 1,
                onClick = { selectedItem = 1 },
                icon = {
                    Icon(imageVector = Icons.Default.Settings, contentDescription = null)
                },
                label = { Text("Settings") }
            )
        },
        modifier = modifier
    ) {
        when (selectedItem) {
            0 -> ListPage(
                onDetailClick = { navController.navigate(route = DetailPage(it)) },
                modifier = Modifier.fillMaxSize()
            )

            1 -> SettingsPage()
        }
    }
}