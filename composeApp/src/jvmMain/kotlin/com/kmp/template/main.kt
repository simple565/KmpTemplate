package com.kmp.template

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.kmp.template.ui.App

fun main() = application {
    val windowState = rememberWindowState(
        position = WindowPosition.Aligned(alignment = Alignment.Center),
        size = DpSize(1080.dp, 800.dp)
    )
    
    Window(
        onCloseRequest = ::exitApplication,
        title = "KmpTemplate",
        state = windowState,
    ) {
        App()
    }
}