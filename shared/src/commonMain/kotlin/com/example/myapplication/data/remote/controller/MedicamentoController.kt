package com.example.myapplication.data.remote.controller

import com.example.myapplication.data.remote.dto.MedicamentoDTO
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class MedicamentoController(
    private val client: SupabaseClient
) {
    suspend fun obtenerMedicamentos(): List<MedicamentoDTO> {
        return client
            .postgrest
            .rpc("obtener_todos_medicamentos")
            .decodeList<MedicamentoDTO>()
    }

    suspend fun obtnerPorNombreGenerico(nombre: String): List<MedicamentoDTO> {
        return client
            .postgrest
            .rpc(
                function = "obtener_medicamento_nombre_generico",
                parameters = buildJsonObject {
                    put("nombre_busqueda", nombre)
                }
            )
            .decodeList<MedicamentoDTO>()
    }
    suspend fun buscarMedicamentosPorSintoma(
        sintoma: String
    ): List<MedicamentoSintomaDTO> {

        return supabase
            .postgrest
            .rpc(
                "buscar_medicamentos_por_sintoma",
                mapOf("sintoma_busqueda" to sintoma)
            )
            .decodeList<MedicamentoSintomaDTO>()
    }
}