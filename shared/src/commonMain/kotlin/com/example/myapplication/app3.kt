package com.example.myapplication

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.di.AppContainer
import com.example.myapplication.presentation.navigation.AppNavigation
import com.example.myapplication.presentation.sintomas.SintomaScreen



@Composable
fun app(container: AppContainer){
    val viewModelMedicamento = remember {
        container.provideMedicamentoViewModel()
    }
    val viewModelSintoma = remember {
        container.providerSintomasMedicamentoViewModel()
    }
    MaterialTheme{
        AppNavigation(
            medicamentoViewModel = viewModelMedicamento,
            sintomasViewModel = viewModelSintoma
        )
    }
}