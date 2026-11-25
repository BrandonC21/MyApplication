package com.example.myapplication.domain.model

data class ProductoFarmacia (
    val nombreFarmacia: String,
    val nombreMedicamento: String,
    val nombreCompletoMedicameto: String,
    val marca: String,
    val precio: Double,
    val precioAnterior: Double,
    val moneda: String,
    val stock: Int,
    val imagen: String,
    val enlace: String,
    val requiereReceta: Boolean
)