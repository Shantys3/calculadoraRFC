package com.example.calculadorarfc

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType


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
            onValueChange = { if (it.all { char -> char.isLetter() || char.isWhitespace() }) nombre = it }, // Validación: Solo letras
            label = { Text("Nombre(s)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next) // Al dar Enter/Tab va al siguiente
        )

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            // CAMPO PATERNO
            OutlinedTextField(
                value = paterno,
                onValueChange = { if (it.all { char -> char.isLetter() || char.isWhitespace() }) paterno = it },
                label = { Text("Apellido Paterno") },
                modifier = Modifier.weight(1f),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )
            // CAMPO MATERNO
            OutlinedTextField(
                value = materno,
                onValueChange = { if (it.all { char -> char.isLetter() || char.isWhitespace() }) materno = it },
                label = { Text("Apellido Materno") },
                modifier = Modifier.weight(1f),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )
        }

        Text("Fecha de Nacimiento (YY MM DD)", style = MaterialTheme.typography.titleMedium)

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                value = anio,
                onValueChange = { if (it.length <= 2 && it.all { char -> char.isDigit() }) anio = it },
                label = { Text("Año") },
                placeholder = { Text("99") },
                modifier = Modifier.weight(1f),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next)
            )
            OutlinedTextField(
                value = mes,
                onValueChange = { if (it.length <= 2 && it.all { char -> char.isDigit() }) mes = it },
                label = { Text("Mes") },
                placeholder = { Text("01") },
                modifier = Modifier.weight(1f),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next)
            )
            OutlinedTextField(
                value = dia,
                onValueChange = { if (it.length <= 2 && it.all { char -> char.isDigit() }) dia = it },
                label = { Text("Día") },
                placeholder = { Text("15") },
                modifier = Modifier.weight(1f),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done)
            )
        }
    }
}