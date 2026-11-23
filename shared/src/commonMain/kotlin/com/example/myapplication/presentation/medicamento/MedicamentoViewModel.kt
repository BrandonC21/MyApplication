
package com.example.myapplication.presentation.medicamento

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.model.Medicamento
import com.example.myapplication.domain.usecase.BuscarMedicamentoUseCase
import com.example.myapplication.domain.usecase.ObtenerMedicamentosUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MedicamentoViewModel (
    private val buscarMedicamentoGen: BuscarMedicamentoUseCase,
    private val obtenerMedicamentos: ObtenerMedicamentosUseCase
): ViewModel(){
    private val _uiState = MutableStateFlow(MedicamentoUiState())

    val uiState: StateFlow<MedicamentoUiState> =
        _uiState.asStateFlow()
    /*
    // 5. Buscar medicamento
    fun buscarMedicamento(nombre: String) {

        viewModelScope.launch {

            _uiState.value = MedicamentoUiState(
                cargando = true
            )

            try {

                val resultado = buscarMedicamentoGen(nombre)

                _uiState.value = MedicamentoUiState(
                    cargando = false,
                    medicamentos = resultado
                )

            } catch (e: Exception) {

                _uiState.value = MedicamentoUiState(
                    cargando = false,
                    error = e.message ?: "Error al buscar medicamentos"
                )
            }
        }
    }

    fun mostrarMedicamentos(){
        viewModelScope.launch {
            try {
                val resultado = obtenerMedicamentos()
                _uiState.value = MedicamentoUiState(
                    medicamentos = resultado
                )
            }
        }
    }
    */
    fun mostrarMedicamentos() {

        viewModelScope.launch {

            _uiState.value = MedicamentoUiState(cargando = true)

            try {
                val resultado = obtenerMedicamentos()
                _uiState.value = MedicamentoUiState(
                    medicamentos = resultado
                )

            } catch (e: Exception) {

                _uiState.value = MedicamentoUiState(
                    error = e.message ?: "Error al obtener medicamentos"
                )
            }
        }
    }

    // Buscar medicamentos
    fun buscarMedicamento(nombre: String) {

        viewModelScope.launch {

            _uiState.value = _uiState.value.copy(
                cargando = true,
                error = null,
                medicamentoSeleccionado = null
            )

            try {
                val resultado = buscarMedicamentoGen(nombre)
                _uiState.value = _uiState.value.copy(
                    cargando = false,
                    medicamentos = resultado
                )

            } catch (e: Exception) {

                _uiState.value = _uiState.value.copy(
                    cargando = false,
                    error = e.message ?: "Error al buscar medicamentos"
                )
            }
        }
    }

    // Seleccionar medicamento
    fun seleccionarMedicamento(medicamento: Medicamento) {
        _uiState.value = _uiState.value.copy(
            medicamentoSeleccionado = medicamento
        )
    }

    // Regresar al listado
    fun regresarListado() {
        _uiState.value = _uiState.value.copy(
            medicamentoSeleccionado = null
        )
    }
}
