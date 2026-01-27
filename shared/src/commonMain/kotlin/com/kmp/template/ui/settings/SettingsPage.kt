package com.kmp.template.ui.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SettingsPage() {
    Box(modifier = Modifier.safeContentPadding().fillMaxSize()) {
        androidx.compose.material3.Text("Settings Page")
    }
}