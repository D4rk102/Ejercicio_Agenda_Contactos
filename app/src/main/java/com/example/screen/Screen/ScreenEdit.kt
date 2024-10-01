package com.example.screen.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.screen.ContactViewModel
import com.example.screen.Contacto

@Composable
fun ScreenEdit(navController: NavController, contactViewModel: ContactViewModel, index: Int) {
    val contacto = contactViewModel.contactos.getOrNull(index) // Recuperar contacto o manejar null
    if (contacto != null) {
        var nombre by remember { mutableStateOf(TextFieldValue(contacto.nombre)) }
        var apellido by remember { mutableStateOf(TextFieldValue(contacto.apellido)) }
        var telefono by remember { mutableStateOf(TextFieldValue(contacto.telefono)) }
        var hobbie by remember { mutableStateOf(TextFieldValue(contacto.hobbie)) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(2.dp)
                .background(Color.Black),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Editar Contacto", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre", color = Color.White) },
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                textStyle = androidx.compose.ui.text.TextStyle(color = Color.White)
            )

            OutlinedTextField(
                value = apellido,
                onValueChange = { apellido = it },
                label = { Text("Apellido", color = Color.White) },
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                textStyle = androidx.compose.ui.text.TextStyle(color = Color.White)
            )

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

            OutlinedTextField(
                value = hobbie,
                onValueChange = { hobbie = it },
                label = { Text("Hobbie", color = Color.White) },
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                textStyle = androidx.compose.ui.text.TextStyle(color = Color.White)
            )

            Button(
                onClick = {
                    // Actualizar el contacto
                    contactViewModel.editarContacto(index, Contacto(nombre.text, apellido.text, telefono.text, hobbie.text))
                    navController.popBackStack() // Volver a la lista de contactos
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
            ) {
                Text("Guardar Cambios")
            }
        }
    } else {
        // Si el contacto es null, mostrar un mensaje o manejar el error
        Text(
            text = "Error: Contacto no encontrado",
            color = Color.Red,
            modifier = Modifier.padding(16.dp)
        )
    }
}
