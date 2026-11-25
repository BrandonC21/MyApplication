package com.example.myapplication.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductoFarmaciaDTO(

    @SerialName("source")
    val farmacia: String? = null,

    @SerialName("query")
    val nombre: String,

    @SerialName("brand")
    val marca: String? = null,

    @SerialName("title")
    val nombreCompleto: String,

    @SerialName("price")
    val precio: Double? = null,

    @SerialName("previous_price")
    val precioAnterior: Double? = null,

    @SerialName("currency")
    val moneda: String? = null,

    @SerialName("stock")
    val stock: Int? = null,

    @SerialName("image_url")
    val imagen: String? = null,

    @SerialName("source_url")
    val enlace: String? = null,

    @SerialName("prescription_required")
    val requiereReceta: Boolean? = null
)
