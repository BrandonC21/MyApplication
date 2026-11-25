package com.example.myapplication.presentation.medicamento

import com.example.myapplication.domain.model.Medicamento
import com.example.myapplication.domain.model.ProductoFarmacia

data class MedicamentoUiState (
    val cargando: Boolean = false,
    val medicamentos: List<Medicamento> = emptyList(),
    val productos: List<ProductoFarmacia> = emptyList(),
    val medicamentoSeleccionado: Medicamento? = null,
    val error: String? = null
) {
    constructor(cargando: Boolean, medicamentos: Any) : this()
}