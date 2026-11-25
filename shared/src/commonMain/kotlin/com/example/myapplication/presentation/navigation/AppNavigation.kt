package com.example.myapplication.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.presentation.medicamento.MedicamentoScreen
import com.example.myapplication.presentation.medicamento.MedicamentoViewModel
import com.example.myapplication.presentation.sintomas.SintomaScreen
import com.example.myapplication.presentation.sintomas.SintomasViewModel
import com.example.myapplication.presentation.vistaScreen

@Composable
fun AppNavigation(
    medicamentoViewModel: MedicamentoViewModel,
    sintomasViewModel: SintomasViewModel
){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Rutas.INICIO
    ) {

        // Pantalla principal
        composable(Rutas.INICIO) {
            vistaScreen(
                onNavigationsearch = {
                    navController.navigate(Rutas.BUSCAR)
                },
                onNavigationSintomas = {
                    navController.navigate(Rutas.SINTOMAS)
                }
            )
        }

        // Pantalla de búsqueda
        composable(Rutas.BUSCAR) {
            MedicamentoScreen(
                viewModel = medicamentoViewModel,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // Pantalla de síntomas
        composable(Rutas.SINTOMAS) {
            SintomaScreen(
                viewModel = sintomasViewModel,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}