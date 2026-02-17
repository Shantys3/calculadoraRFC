package com.example.calculadorarfc

import androidx.compose.ui.unit.dp // Importante importar esto
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    val windowState = rememberWindowState(width = 600.dp, height = 700.dp)

    Window(
        onCloseRequest = ::exitApplication,
        title = "Calculadora RFC - ITSUR",
        state = windowState,
        resizable = false
    ) {
        App()
    }
}