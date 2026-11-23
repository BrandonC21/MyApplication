package com.example.myapplication.domain.model

data class MedicamentoSintoma (
    val nombreGenerico: String,
    val nombreComercial: String?,
    val grupoFarmacologico: String,
    val presentacion: String?,
    val mecanismoAccion: String?,
    val viaAdministracion: String?,
    val indicaciones: String?,
    val contraindicaciones: String?,
    val efectosAdversos: String?,
    val posologia: String?,
    val relevancia: Float
)