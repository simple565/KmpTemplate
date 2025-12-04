package com.kmp.template

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.kmp.template.ui.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KmpTemplate",
    ) {
        App()
    }
}