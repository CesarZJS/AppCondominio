package com.example.appcondominio.data

data class ConceptoPago(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val monto: Double,
    val periodicidad: String,
    val activo: Boolean,
    val propietarioId: String // Torre + Departamento como identificador
)