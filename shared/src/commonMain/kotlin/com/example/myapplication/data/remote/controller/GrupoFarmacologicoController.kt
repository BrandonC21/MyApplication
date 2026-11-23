package com.example.myapplication.data.remote.controller

import com.example.myapplication.data.remote.dto.GrupoFarmacologicoDTO
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from

class GrupoFarmacologicoController(
    private val client: SupabaseClient
) {
    suspend fun obtenerTodos(): List<GrupoFarmacologicoDTO> {
        return client
            .from("grupos_farmacologicos")
            .select()
            .decodeList<GrupoFarmacologicoDTO>()
    }
}