package com.example.myapplication.presentation.medicamento

import com.example.myapplication.domain.model.Medicamento

data class MedicamentoUiState (
    val cargando: Boolean = false,
    val medicamentos: List<Medicamento> = emptyList(),
    val medicamentoSeleccionado: Medicamento? = null,
    val error: String? = null
) {
    constructor(cargando: Boolean, medicamentos: Any) : this()
}