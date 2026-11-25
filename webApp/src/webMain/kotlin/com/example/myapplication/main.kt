package com.example.myapplication

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.example.myapplication.di.AppContainer

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport {
        val appContainer = AppContainer()
        app(appContainer)
    }
}