package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.Medicamento
import com.example.myapplication.domain.repository.MedicamentoRepository

class BuscarMedicamentoUseCase (
    private val repository: MedicamentoRepository
){
    suspend operator fun invoke(
        nombre: String
    ): List<Medicamento>{
        return repository.obtenerPorNombreGen(nombre)
    }
}