package com.example.myapplication.di
import com.example.myapplication.config.ConexionAPI
import com.example.myapplication.config.SupabaseProvider
import com.example.myapplication.data.remote.controller.ApifyFarmaciaController
import com.example.myapplication.data.remote.controller.MedicamentoController
import com.example.myapplication.data.repository.MedicamentoRepositoryImpl
import com.example.myapplication.data.repository.ProductoRepositoryImp
import com.example.myapplication.domain.usecase.BuscarMedicamentoPorSintomaUseCase
import com.example.myapplication.domain.usecase.BuscarMedicamentoUseCase
import com.example.myapplication.domain.usecase.GetProductosFarmaciaUseCase
import com.example.myapplication.domain.usecase.ObtenerMedicamentosUseCase
import com.example.myapplication.presentation.medicamento.MedicamentoViewModel
import com.example.myapplication.presentation.sintomas.SintomasViewModel

class AppContainer {
    // Obtner a la Conexion a la base
    private val supabase = SupabaseProvider.supabase
    //Conexion de la API
    private val apiConfig = ConexionAPI
    // Conectar el contoller
    private val medicamentoController = MedicamentoController(supabase)
    //Conectar el controoler del la api
    private val apifyFarmaciaController = ApifyFarmaciaController(
        httpClient = apiConfig.httpClient
    )
    //Crear repo
    private val medicamentoRepository = MedicamentoRepositoryImpl(medicamentoController)

    private val obtencionAPIRepository = ProductoRepositoryImp(apifyFarmaciaController)
    //User Case
    private val buscarMedicamentoUseCase = BuscarMedicamentoUseCase(medicamentoRepository)

    private val obtenerMedicamentosUseCase = ObtenerMedicamentosUseCase(medicamentoRepository)

    private val buscarMedicamentoPorSintomaUseCase = BuscarMedicamentoPorSintomaUseCase(medicamentoRepository)

    private val getProductosFarmaciaUseCase = GetProductosFarmaciaUseCase(obtencionAPIRepository)
    fun provideMedicamentoViewModel(): MedicamentoViewModel {
        return MedicamentoViewModel(buscarMedicamentoUseCase, obtenerMedicamentosUseCase,getProductosFarmaciaUseCase)
    }
    //Obtner sintomas y mostrar la sugerencia de un medicamento
    fun providerSintomasMedicamentoViewModel(): SintomasViewModel{
        return SintomasViewModel(buscarMedicamentoPorSintomaUseCase)
    }


}
