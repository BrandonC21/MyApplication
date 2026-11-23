package com.example.myapplication.presentation.sintomas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.domain.model.MedicamentoSintoma

@Composable
fun SintomaScreen(
    viewModel: SintomaViewModel
) {

    val uiState by viewModel.uiState.collectAsState()

    var sintoma by remember {
        mutableStateOf("")
    }

    var seleccionado by remember {
        mutableStateOf<MedicamentoSintoma?>(null)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        if (seleccionado != null) {

            Button(
                onClick = {
                    seleccionado = null
                }
            ) {
                Text("Regresar")
            }

            Text(
                text = seleccionado!!.nombreGenerico,
                style = MaterialTheme.typography.headlineMedium
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    val medicamento = seleccionado!!

                    CampoDetalle("Nombre comercial",
                        medicamento.nombreComercial)

                    CampoDetalle("Grupo farmacológico",
                        medicamento.grupoFarmacologico)

                    CampoDetalle("Presentación",
                        medicamento.presentacion)

                    CampoDetalle("Mecanismo de acción",
                        medicamento.mecanismoAccion)

                    CampoDetalle("Vía de administración",
                        medicamento.viaAdministracion)

                    CampoDetalle("Indicaciones",
                        medicamento.indicaciones)

                    CampoDetalle("Contraindicaciones",
                        medicamento.contraindicaciones)

                    CampoDetalle("Efectos adversos",
                        medicamento.efectosAdversos)

                    CampoDetalle("Posología",
                        medicamento.posologia)
                }
            }

        } else {

            Text(
                text = "Consulta por síntomas",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Ingresa los síntomas que deseas consultar.",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = sintoma,
                onValueChange = {
                    sintoma = it
                },
                label = {
                    Text("Ej. dolor de cabeza y fiebre")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    viewModel.buscarPorSintoma(sintoma)
                },
                enabled = sintoma.isNotBlank() && !uiState.cargando
            ) {
                Text("Consultar")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Las coincidencias son informativas y no constituyen recomendaciones de tratamiento.",
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(12.dp))

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

                uiState.resultados.isEmpty() -> {
                    Text("No se encontraron coincidencias")
                }

                else -> {

                    Text(
                        text = "Coincidencias encontradas: ${uiState.resultados.size}",
                        style = MaterialTheme.typography.titleMedium
                    )

                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {

                        items(uiState.resultados) { medicamento ->

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        seleccionado = medicamento
                                    }
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

@Composable
fun CampoDetalle(
    titulo: String,
    contenido: String?
) {
    Column(
        modifier = Modifier.padding(vertical = 6.dp)
    ) {
        Text(
            text = titulo,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = contenido ?: "No disponible"
        )
    }
}
