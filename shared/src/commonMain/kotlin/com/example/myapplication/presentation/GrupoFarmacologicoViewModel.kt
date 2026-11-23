package com.example.myapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.config.SupabaseProvider
import com.example.myapplication.data.remote.controller.GrupoFarmacologicoController
import com.example.myapplication.data.remote.dto.GrupoFarmacologicoDTO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GrupoFarmacologicoViewModel: ViewModel() {
    private val controller = GrupoFarmacologicoController(
        SupabaseProvider.supabase
    )
    private val _grupos = MutableStateFlow<List<GrupoFarmacologicoDTO>>(emptyList())
    val grupos: StateFlow<List<GrupoFarmacologicoDTO>> = _grupos.asStateFlow()

    private val _cargando = MutableStateFlow(false)
    val cargando: StateFlow<Boolean> = _cargando.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        obtnerGrupos()
    }
    fun obtnerGrupos(){
        viewModelScope.launch{
            _cargando.value = true
            _error.value = null
            try {
                _grupos.value = controller.obtenerTodos()
            } catch (e: Exception){
                _error.value = e.message
            } finally {
                _cargando.value = false
            }
        }
    }
}