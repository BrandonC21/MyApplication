package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.MedicamentoSintoma
import com.example.myapplication.domain.repository.MedicamentoRepository

class BuscarMedicamentoPorSintomaUseCase(
    private val repository: MedicamentoRepository
) {

    suspend operator fun invoke(
        sintoma: String
    ): List<MedicamentoSintoma> {

        return repository.buscarMedicamentosPorSintoma(sintoma)
    }
}