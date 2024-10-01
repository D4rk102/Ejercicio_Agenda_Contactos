package com.example.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

// Modelo de datos de Contacto
data class Contacto(val nombre: String, val apellido: String, val telefono: String, val hobbie: String)

class ContactViewModel : ViewModel() {
    val contactos = mutableStateListOf<Contacto>()

    // Función para agregar un contacto
    fun agregarContacto(contacto: Contacto) {
        contactos.add(contacto)
    }

    // Función para eliminar un contacto
    fun eliminarContacto(contacto: Contacto) {
        contactos.remove(contacto)
    }

    // Función para editar un contacto
    fun editarContacto(index: Int, nuevoContacto: Contacto) {
        if (index in contactos.indices) {
            contactos[index] = nuevoContacto
        }
    }
}

