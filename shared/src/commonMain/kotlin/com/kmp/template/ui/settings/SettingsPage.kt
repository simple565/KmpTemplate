package com.kmp.template.ui.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SettingsPage(modifier: Modifier = Modifier) {
    Box(modifier = modifier.systemBarsPadding()) {
        Text("Settings Page")
    }
}