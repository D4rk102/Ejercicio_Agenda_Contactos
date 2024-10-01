package com.example.screen.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.screen.ContactViewModel

@Composable
fun ScreenB(navController: NavController, contactViewModel: ContactViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(2.dp)
            .background(Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Lista de Contactos",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        contactViewModel.contactos.forEachIndexed { index, contacto ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color.White.copy(alpha = 0.1f))
                    .padding(16.dp)
            ) {
                Text(
                    text = "${contacto.nombre} ${contacto.apellido}",
                    color = Color.Blue,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Teléfono: ${contacto.telefono}",
                    color = Color.White,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Text(
                    text = "Hobbie: ${contacto.hobbie}",
                    color = Color.White,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )

                Row(modifier = Modifier.padding(top = 8.dp)) {
                    // Botón para editar
                    Button(
                        onClick = {
                            navController.navigate("screen-edit/$index") // Pasar el índice
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
                    ) {
                        Text("Editar")
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    // Botón para eliminar
                    Button(
                        onClick = { contactViewModel.eliminarContacto(contacto) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        Text("Eliminar")
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = { navController.popBackStack() },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        ) {
            Text(text = "Volver a Registrar", color = Color.White)
        }
    }
}
