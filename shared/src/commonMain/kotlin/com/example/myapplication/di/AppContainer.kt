package com.example.myapplication.di
import com.example.myapplication.config.SupabaseProvider
import com.example.myapplication.data.remote.controller.MedicamentoController
import com.example.myapplication.data.repository.MedicamentoRepositoryImpl
import com.example.myapplication.domain.usecase.BuscarMedicamentoUseCase
import com.example.myapplication.domain.usecase.ObtenerMedicamentosUseCase
import com.example.myapplication.presentation.medicamento.MedicamentoViewModel

class AppContainer {
    // Obtner a la Conexion a la base
    private val supabase = SupabaseProvider.supabase

    // Conectar el contoller
    private val medicamentoController = MedicamentoController(supabase)

    //Crear repo
    private val medicamentoRepository = MedicamentoRepositoryImpl(medicamentoController)

    //User Case
    private val buscarMedicamentoUseCase = BuscarMedicamentoUseCase(medicamentoRepository)

    private val obtenerMedicamentosUseCase = ObtenerMedicamentosUseCase(medicamentoRepository)

    fun provideMedicamentoViewModel(): MedicamentoViewModel {
        return MedicamentoViewModel(buscarMedicamentoUseCase, obtenerMedicamentosUseCase)
    }
}
