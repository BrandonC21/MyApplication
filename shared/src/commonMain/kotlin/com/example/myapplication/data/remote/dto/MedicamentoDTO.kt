package com.example.myapplication.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class MedicamentoDTO (
    //val id: Int,
    val nombre_generico: String,
    val nombre_comercial: String,
    //val grupo_farmacologico_id: Int,
    val grupo_farmacologico: String,
    //val grupo_farmacologico: Int,
    val presentacion: String,
    val mecanismo_accion: String,
    val via_administracion: String,
    val indicaciones: String,
    val contraindicaciones: String,
    val efectos_adversos: String,
    val posologia: String
)