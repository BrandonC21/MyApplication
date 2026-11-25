package com.example.myapplication.data.mapper

import com.example.myapplication.data.remote.dto.ProductoFarmaciaDTO
import com.example.myapplication.domain.model.ProductoFarmacia

fun ProductoFarmaciaDTO.toDomain(): ProductoFarmacia {
    return ProductoFarmacia(
        nombreFarmacia = farmacia ?: "Desconocida",
        nombreMedicamento = nombre,
        nombreCompletoMedicameto = nombreCompleto,
        marca = marca ?: "Sin marca",
        precio = precio ?: 0.0,
        precioAnterior = precioAnterior ?: 0.0,
        moneda = moneda ?: "MXN",
        stock = stock ?: 0,
        imagen = imagen ?: "",
        enlace = enlace ?: "",
        requiereReceta = requiereReceta ?: false
    )
}