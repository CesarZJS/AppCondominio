package com.example.appcondominio.data

data class HorarioDia(
    val dia: String,
    var desde: String,
    var hasta: String,
    var estado: String // "disponible", "mantenimiento", "reservado"
)