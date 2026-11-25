package com.example.myapplication

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.di.AppContainer
import com.example.myapplication.presentation.medicamento.MedicamentoScreen


@Composable
fun App2(container: AppContainer){
    val viewModel = remember {
        container.provideMedicamentoViewModel()
    }
    MaterialTheme {
        //MedicamentoScreen(
          //  viewModel = viewModel
        //)
    }
}