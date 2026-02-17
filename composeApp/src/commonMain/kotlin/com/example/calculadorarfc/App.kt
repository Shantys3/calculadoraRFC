package com.example.calculadorarfc

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
@Preview
fun App() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            RfcScreen()
        }
    }
}

@Composable
fun RfcScreen() {
    var nombre by remember { mutableStateOf("") }
    var paterno by remember { mutableStateOf("") }
    var materno by remember { mutableStateOf("") }

    var anio by remember { mutableStateOf("") }
    var mes by remember { mutableStateOf("") }
    var dia by remember { mutableStateOf("") }

    val rfcCalculado by remember {
        derivedStateOf {
            // Concatenamos la fecha tal cual está
            val fechaConcatenada = "$anio$mes$dia"
            RfcLogic.calcularRFC(nombre, paterno, materno, fechaConcatenada)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Calculadora de RFC",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp).fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("RFC Generado:", style = MaterialTheme.typography.labelLarge)
                Text(
                    text = rfcCalculado,
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre(s)") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            OutlinedTextField(
                value = paterno,
                onValueChange = { paterno = it },
                label = { Text("Apellido Paterno") },
                modifier = Modifier.weight(1f)
            )
            OutlinedTextField(
                value = materno,
                onValueChange = { materno = it },
                label = { Text("Apellido Materno") },
                modifier = Modifier.weight(1f)
            )
        }

        Text("Fecha de Nacimiento (YY MM DD)", style = MaterialTheme.typography.titleMedium)

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            // CAMPO AÑO
            OutlinedTextField(
                value = anio,
                onValueChange = { input ->
                    // Clausula: Solo acepta si son números Y máximo 2 caracteres
                    if (input.length <= 2 && input.all { it.isDigit() }) {
                        anio = input
                    }
                },
                label = { Text("Año") },
                placeholder = { Text("99") },
                singleLine = true,
                modifier = Modifier.weight(1f)
            )

            // CAMPO MES
            OutlinedTextField(
                value = mes,
                onValueChange = { input ->
                    if (input.length <= 2 && input.all { it.isDigit() }) {
                        mes = input
                    }
                },
                label = { Text("Mes") },
                placeholder = { Text("01") },
                singleLine = true,
                modifier = Modifier.weight(1f)
            )

            // CAMPO DÍA
            OutlinedTextField(
                value = dia,
                onValueChange = { input ->
                    if (input.length <= 2 && input.all { it.isDigit() }) {
                        dia = input
                    }
                },
                label = { Text("Día") },
                placeholder = { Text("15") },
                singleLine = true,
                modifier = Modifier.weight(1f)
            )
        }
    }
}