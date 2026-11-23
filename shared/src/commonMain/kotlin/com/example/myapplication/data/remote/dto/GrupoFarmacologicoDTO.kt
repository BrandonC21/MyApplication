package com.example.myapplication.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class GrupoFarmacologicoDTO (
    val id: Int,
    val nombre: String
)

