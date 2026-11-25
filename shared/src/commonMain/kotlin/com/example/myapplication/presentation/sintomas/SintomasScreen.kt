package com.example.myapplication.presentation.sintomas

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myapplication.domain.model.MedicamentoSintoma

@Composable
fun SintomaScreen(
    viewModel: SintomasViewModel,
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    var sintoma by remember {
        mutableStateOf("")
    }

    var seleccionado by remember {
        mutableStateOf<MedicamentoSintoma?>(null)
    }

    // Detectar el tema del dispositivo
    val isDark = isSystemInDarkTheme()

    // Colores dinámicos según el tema (Igual que en MedicamentoScreen)
    val fondo = if (isDark) Color(0xFF101418) else Color(0xFFF4F7FB)
    val titleColor = if (isDark) Color(0xFF778899) else Color(0xFF0F172A)
    val bannerColor = if (isDark) Color(0xFF101B3D) else Color(0xFF191970)
    val tarjetaColor = if (isDark) Color(0xFF252A35) else Color(0xFFE6E0EA)
    val colorBoton = if (isDark) Color(0xFF101B3D) else Color(0xFF191970)

    // Colores derivados para etiquetas, valores y enlaces
    val labelColor = if (isDark) Color(0xFF94A3B8) else Color(0xFF475569)
    val valueColor = if (isDark) Color(0xFFF1F5F9) else Color(0xFF0F172A)
    val linkColor = if (isDark) Color(0xFF60A5FA) else Color(0xFF1D4ED8)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(fondo)
            .statusBarsPadding()
            .padding(
                horizontal = 20.dp,
                vertical = 16.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        if (seleccionado != null) {
            // VISTA DE DETALLE DEL MEDICAMENTO SELECCIONADO POR SÍNTOMA
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Button(
                    onClick = { seleccionado = null },
                    colors = ButtonDefaults.buttonColors(containerColor = colorBoton)
                ) {
                    Text("← Regresar")
                }
            }

            Text(
                text = seleccionado!!.nombreGenerico,
                style = MaterialTheme.typography.headlineMedium,
                color = titleColor,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    val medicamento = seleccionado!!

                    CampoDetalle("Nombre comercial", medicamento.nombreComercial, labelColor, valueColor)
                    CampoDetalle("Grupo farmacológico", medicamento.grupoFarmacologico, labelColor, valueColor)
                    CampoDetalle("Presentación", medicamento.presentacion, labelColor, valueColor)
                    CampoDetalle("Mecanismo de acción", medicamento.mecanismoAccion, labelColor, valueColor)
                    CampoDetalle("Vía de administración", medicamento.viaAdministracion, labelColor, valueColor)
                    CampoDetalle("Indicaciones", medicamento.indicaciones, labelColor, valueColor)
                    CampoDetalle("Contraindicaciones", medicamento.contraindicaciones, labelColor, valueColor)
                    CampoDetalle("Efectos adversos", medicamento.efectosAdversos, labelColor, valueColor)
                    CampoDetalle("Posología", medicamento.posologia, labelColor, valueColor)
                }
            }

        } else {
            // VISTA PRINCIPAL (BÚSQUEDA Y RESULTADOS)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(bannerColor, shape = RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Regresar",
                        tint = Color.White
                    )
                }

                Text(
                    text = "Consulta por síntomas",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineMedium
                )
            }

            Text(
                text = "Ingresa los síntomas que deseas consultar.",
                style = MaterialTheme.typography.bodyMedium,
                color = valueColor
            )

            OutlinedTextField(
                value = sintoma,
                onValueChange = { sintoma = it },
                label = { Text("Ej. dolor de cabeza y fiebre") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = { viewModel.buscarPorSintoma(sintoma) },
                    modifier = Modifier
                        .fillMaxWidth(0.80f)
                        .height(45.dp),
                    shape = RoundedCornerShape(25.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorBoton,
                        contentColor = Color.White
                    ),
                    enabled = sintoma.isNotBlank() && !uiState.cargando
                ) {
                    Text("Consultar")
                }
            }

            Text(
                text = "Las coincidencias son informativas y no constituyen recomendaciones de tratamiento.",
                style = MaterialTheme.typography.bodySmall,
                color = labelColor
            )

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
                    Text(
                        text = "No se encontraron coincidencias",
                        textAlign = TextAlign.Start,
                        color = valueColor,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                else -> {
                    Text(
                        text = "Coincidencias encontradas: ${uiState.resultados.size}",
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Start,
                        color = titleColor,
                        modifier = Modifier.fillMaxWidth()
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
                                    },
                                colors = CardDefaults.cardColors(containerColor = tarjetaColor),
                                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text(
                                        text = "Nombre Genérico",
                                        style = MaterialTheme.typography.titleMedium,
                                        color = labelColor
                                    )
                                    Text(
                                        text = medicamento.nombreGenerico,
                                        color = valueColor
                                    )

                                    Spacer(modifier = Modifier.height(4.dp))

                                    Text(
                                        text = "Nombre Comercial",
                                        style = MaterialTheme.typography.titleMedium,
                                        color = labelColor
                                    )
                                    Text(
                                        text = medicamento.nombreComercial ?: "Sin nombre comercial",
                                        color = valueColor
                                    )

                                    Spacer(modifier = Modifier.height(4.dp))

                                    Text(
                                        text = "Grupo Farmacológico",
                                        style = MaterialTheme.typography.titleMedium,
                                        color = labelColor
                                    )
                                    Text(
                                        text = medicamento.grupoFarmacologico ?: "No especificado",
                                        color = valueColor
                                    )

                                    Spacer(modifier = Modifier.height(6.dp))

                                    Text(
                                        text = "Ver información completa →",
                                        color = linkColor,
                                        style = MaterialTheme.typography.labelLarge
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
    contenido: String?,
    labelColor: Color,
    valueColor: Color
) {
    Column(
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Text(
            text = titulo,
            style = MaterialTheme.typography.titleSmall,
            color = labelColor
        )
        Text(
            text = contenido ?: "No disponible",
            style = MaterialTheme.typography.bodyLarge,
            color = valueColor
        )
    }
}