package com.example.myapplication.config

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

object SupabaseProvider {
    val supabase by lazy {
        createSupabaseClient(
            supabaseUrl = "https://yokyysazuxuwgvefyufi.supabase.co",
            supabaseKey = "sb_publishable_RGZUUPgNUtTQiOJ4i68suQ_luniIBO1"
        ) {
            install(Postgrest)
            install(Auth)
        }
    }

    //Conexion de la api
}