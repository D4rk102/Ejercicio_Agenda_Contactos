package com.example.screen.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.screen.ContactViewModel
import com.example.screen.Contacto
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun ScreenA(navController: NavController, contactViewModel: ContactViewModel = viewModel()) {
    var nombre by remember { mutableStateOf(TextFieldValue()) }
    var apellido by remember { mutableStateOf(TextFieldValue()) }
    var telefono by remember { mutableStateOf(TextFieldValue()) }
    var hobbie by remember { mutableStateOf(TextFieldValue()) }
    var showError by remember { mutableStateOf(false) } // Estado para mostrar error

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(2.dp)
            .background(Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título "Agenda de Contactos"
        Text(
            text = "Agenda de Contactos",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Campo de texto para "Nombre"
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre", color = Color.White) },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            textStyle = androidx.compose.ui.text.TextStyle(color = Color.White)
        )

        // Campo de texto para "Apellido"
        OutlinedTextField(
            value = apellido,
            onValueChange = { apellido = it },
            label = { Text("Apellido", color = Color.White) },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            textStyle = androidx.compose.ui.text.TextStyle(color = Color.White)
        )

        // Campo de texto para "Teléfono"
        OutlinedTextField(
            value = telefono,
            onValueChange = {
                if (it.text.length <= 10 && it.text.all { char -> char.isDigit() }) {
                    telefono = it
                }
            },
            label = { Text("Teléfono", color = Color.White) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            textStyle = androidx.compose.ui.text.TextStyle(color = Color.White),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )


        // Campo de texto para "Hobbie"
        OutlinedTextField(
            value = hobbie,
            onValueChange = { hobbie = it },
            label = { Text("Hobbie", color = Color.White) },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            textStyle = androidx.compose.ui.text.TextStyle(color = Color.White)
        )

        // Mostrar mensaje de error si hay campos vacíos
        if (showError) {
            Text(
                text = "Por favor, completa todos los campos.",
                color = Color.Red,
                modifier = Modifier.padding(top = 16.dp)
            )
        }

        // Botón para registrar el contacto
        Button(
            onClick = {
                // Validar que todos los campos estén llenos
                if (nombre.text.isEmpty() || apellido.text.isEmpty() || telefono.text.isEmpty() || hobbie.text.isEmpty()) {
                    showError = true // Mostrar error si algún campo está vacío
                } else {
                    // Agregar el contacto al ViewModel
                    contactViewModel.agregarContacto(
                        Contacto(
                            nombre = nombre.text,
                            apellido = apellido.text,
                            telefono = telefono.text,
                            hobbie = hobbie.text
                        )
                    )
                    // Navegar a la pantalla B
                    navController.navigate("screen-b")
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue, // Color de fondo del botón
                contentColor = Color.White // Color del texto del botón
            ),
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Registrar") // Texto del botón
        }
    }
}
