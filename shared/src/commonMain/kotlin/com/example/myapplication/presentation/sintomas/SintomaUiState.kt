package com.example.myapplication.presentation.sintomas

import com.example.myapplication.domain.model.MedicamentoSintoma

data class SintomaUiState(
    val cargando: Boolean = false,
    val resultados: List<MedicamentoSintoma> = emptyList(),
    val error: String? = null
)