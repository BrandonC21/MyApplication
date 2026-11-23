package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.Medicamento
import com.example.myapplication.domain.model.MedicamentoSintoma

interface MedicamentoRepository {
    suspend fun obtenerPorNombreGen(
        nombre: String
    ): List<Medicamento>

    suspend fun obtenerMedicamento():
            List<Medicamento>
    
    suspend fun buscarMedicamentosPorSintoma(
        sintoma: String
    ): List<MedicamentoSintoma>
}