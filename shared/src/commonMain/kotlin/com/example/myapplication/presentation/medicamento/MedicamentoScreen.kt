
package com.example.myapplication.presentation.medicamento

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MedicamentoScreen(
    viewModel: MedicamentoViewModel
) {

    val uiState by viewModel.uiState.collectAsState()

    var nombre by remember {
        mutableStateOf("")
    }

    // Obtener medicamentos al entrar a la pantalla
    LaunchedEffect(Unit) {
        viewModel.mostrarMedicamentos()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // Si existe un medicamento seleccionado,
        // mostramos sus detalles.
        val seleccionado = uiState.medicamentoSeleccionado

        if (seleccionado != null) {

            Button(
                onClick = {
                    viewModel.regresarListado()
                }
            ) {
                Text("Regresar")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = seleccionado.nombreGenerico,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            DetalleMedicamento(seleccionado)

        } else {

            // LISTADO DE MEDICAMENTOS

            Text(
                text = "Medicamentos",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = nombre,
                onValueChange = {
                    nombre = it
                },
                label = {
                    Text("Buscar medicamento")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    if (nombre.isBlank()) {
                        viewModel.mostrarMedicamentos()
                    } else {
                        viewModel.buscarMedicamento(nombre)
                    }
                },
                enabled = !uiState.cargando
            ) {
                Text("Buscar")
            }

            Spacer(modifier = Modifier.height(16.dp))

            when {

                uiState.cargando -> {
                    CircularProgressIndicator()
                }

                uiState.error != null -> {
                    Text(
                        text = uiState.error ?: "Error",
                        color = MaterialTheme.colorScheme.error
                    )
                }

                uiState.medicamentos.isEmpty() -> {
                    Text("No hay medicamentos disponibles")
                }

                else -> {

                    Text(
                        text = "Resultados: ${uiState.medicamentos.size}",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {

                        items(uiState.medicamentos) { medicamento ->

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        viewModel.seleccionarMedicamento(
                                            medicamento
                                        )
                                    },
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 4.dp
                                )
                            ) {

                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {

                                    Text(
                                        text = medicamento.nombreGenerico,
                                        style = MaterialTheme.typography.titleMedium
                                    )

                                    Text(
                                        text = medicamento.nombreComercial
                                            ?: "Sin nombre comercial"
                                    )

                                    Text(
                                        text = "Ver información completa →",
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

// COMPONENTE PARA MOSTRAR TODOS LOS DATOS

@Composable
fun DetalleMedicamento(
    medicamento: com.example.myapplication.domain.model.Medicamento
) {

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        item {
            InformacionCampo(
                "Nombre genérico",
                medicamento.nombreGenerico
            )

            InformacionCampo(
                "Nombre comercial",
                medicamento.nombreComercial
            )

            InformacionCampo(
                "Grupo farmacológico",
                medicamento.grupoFarmacologico.toString()
            )

            InformacionCampo(
                "Presentación",
                medicamento.presentacion
            )

            InformacionCampo(
                "Mecanismo de acción",
                medicamento.mecanismoAccion
            )

            InformacionCampo(
                "Vía de administración",
                medicamento.viaAdministracion
            )

            InformacionCampo(
                "Indicaciones",
                medicamento.indicaciones
            )

            InformacionCampo(
                "Contraindicaciones",
                medicamento.contraindicaciones
            )

            // Agrega aquí los demás campos
            // que tengas definidos en tu modelo.
        }
    }
}

@Composable
fun InformacionCampo(
    titulo: String,
    contenido: String?
) {

    Column {

        Text(
            text = titulo,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = contenido ?: "No disponible",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
