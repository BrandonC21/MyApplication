package com.example.myapplication.data.mapper

import com.example.myapplication.data.remote.dto.MedicamentoDTO
import com.example.myapplication.domain.model.Medicamento

fun MedicamentoDTO.toDomain(): Medicamento {

    return Medicamento(
        nombreGenerico = nombre_generico,
        nombreComercial = nombre_comercial,
        grupoFarmacologico = grupo_farmacologico,
        presentacion = presentacion,
        mecanismoAccion = mecanismo_accion,
        viaAdministracion = via_administracion,
        indicaciones = indicaciones,
        contraindicaciones = contraindicaciones,
        efectosAdversos = efectos_adversos,
        posologia = posologia
    )
}
