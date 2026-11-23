package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.myapplication.di.AppContainer


fun main() = application {
    val container = AppContainer()

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "MyApplication"
        ) {
            App2(container)
        }
    }

}
@Preview(showBackground = true)
@Composable
fun AppAndroidPreview() {
    app()
}