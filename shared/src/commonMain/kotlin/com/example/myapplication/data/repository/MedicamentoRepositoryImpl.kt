package com.example.myapplication.data.repository

import com.example.myapplication.data.mapper.toDomain
import com.example.myapplication.data.remote.controller.MedicamentoController
import com.example.myapplication.domain.model.Medicamento
import com.example.myapplication.domain.model.MedicamentoSintoma
import com.example.myapplication.domain.repository.MedicamentoRepository

class MedicamentoRepositoryImpl(
    private val controller: MedicamentoController
): MedicamentoRepository{
    override suspend fun obtenerPorNombreGen(nombre: String): List<Medicamento> {
        return controller
            .obtnerPorNombreGenerico(nombre)
            .map{it.toDomain()}
    }

    override suspend fun obtenerMedicamento(): List<Medicamento> {
        return controller
            .obtenerMedicamentos()
            .map { it.toDomain()}
    }

    override suspend fun buscarMedicamentosPorSintoma(sintoma: String): List<MedicamentoSintoma> {
        return controller.buscarMedicamentosPorSintoma(sintoma)
            .map { it.toDomain() }
    }

}

