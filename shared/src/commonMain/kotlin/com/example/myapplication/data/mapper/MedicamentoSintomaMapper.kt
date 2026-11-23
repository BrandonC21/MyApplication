package com.example.myapplication.data.mapper

import com.example.myapplication.data.remote.dto.MedicamentoSintomaDTO
import com.example.myapplication.domain.model.MedicamentoSintoma

fun MedicamentoSintomaDTO.toDomain(): MedicamentoSintoma {

    return MedicamentoSintoma(
        nombreGenerico = nombre_generico,
        nombreComercial = nombre_comercial,
        grupoFarmacologico = grupo_farmacologico,
        presentacion = presentacion,
        mecanismoAccion = mecanismo_accion,
        viaAdministracion = via_administracion,
        indicaciones = indicaciones,
        contraindicaciones = contraindicaciones,
        efectosAdversos = efectos_adversos,
        posologia = posologia,
        relevancia = relevancia
    )
}