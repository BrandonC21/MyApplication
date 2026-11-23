package com.example.myapplication.presentation.sintomas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.usecase.BuscarMedicamentoPorSintomaUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SintomaViewModel(
    private val buscarMedicamentoPorSintoma: BuscarMedicamentoPorSintomaUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SintomaUiState())
    val uiState = _uiState.asStateFlow()

    fun buscarPorSintoma(sintoma: String) {

        if (sintoma.isBlank()) return

        viewModelScope.launch {

            _uiState.value = _uiState.value.copy(
                cargando = true,
                error = null,
                resultados = emptyList()
            )

            try {

                val resultado = buscarMedicamentoPorSintoma(sintoma)

                _uiState.value = _uiState.value.copy(
                    cargando = false,
                    resultados = resultado
                )

            } catch (e: Exception) {

                _uiState.value = _uiState.value.copy(
                    cargando = false,
                    error = e.message ?: "Error al buscar medicamentos"
                )
            }
        }
    }
}