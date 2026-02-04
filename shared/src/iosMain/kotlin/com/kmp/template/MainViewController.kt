package com.kmp.template

import androidx.compose.ui.window.ComposeUIViewController
import com.kmp.template.di.initKoin
import com.kmp.template.ui.App

fun MainViewController() = ComposeUIViewController(configure = { initKoin() }) { App() }