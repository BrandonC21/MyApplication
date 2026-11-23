package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.Medicamento
import com.example.myapplication.domain.repository.MedicamentoRepository

class ObtenerMedicamentosUseCase(
    private val repository: MedicamentoRepository
){
    suspend operator fun invoke():
        List<Medicamento>{
        return repository.obtenerMedicamento()
    }
}