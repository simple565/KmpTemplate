package com.kmp.template.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.elife.room.ui.theme.AppTheme
import com.kmp.template.ui.home.HomePage
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

private val config = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(HomePage::class, HomePage.serializer())
            subclass(DetailPage::class, DetailPage.serializer())
        }
    }
}

@Composable
fun App() {
    val topLevelBackStack = rememberNavBackStack(config,HomePage)

    AppTheme {
        NavDisplay(
            backStack = topLevelBackStack,
            onBack = { topLevelBackStack.removeLastOrNull() },
            entryProvider = entryProvider {
                entry<HomePage> {
                    HomePage(
                        onDetailClick = { topLevelBackStack.add(DetailPage(it)) },
                        modifier = Modifier.fillMaxSize()
                    )
                }
                entry<DetailPage> {
                    DetailPage(
                        id = it.id,
                        onBackClick = { topLevelBackStack.removeLastOrNull() },
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        )
    }
}