package com.example.myapplication.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class MedicamentoSintomaDTO (
    val nombre_generico: String,
    val nombre_comercial: String? = null,
    val grupo_farmacologico: String,
    val presentacion: String? = null,
    val mecanismo_accion: String? = null,
    val via_administracion: String? = null,
    val indicaciones: String? = null,
    val contraindicaciones: String? = null,
    val efectos_adversos: String? = null,
    val posologia: String? = null,
    val relevancia: Float
)
