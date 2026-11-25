package com.example.myapplication.presentation.medicamento

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.myapplication.domain.model.ProductoFarmacia
import myapplication.shared.generated.resources.No_Requiere_Receta
import myapplication.shared.generated.resources.Res
import org.jetbrains.compose.resources.stringResource
import myapplication.shared.generated.resources.Requiere_receta

@Composable
fun MedicamentoScreen(
    viewModel: MedicamentoViewModel,
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    var nombre by remember {
        mutableStateOf("")
    }

    // Obtener medicamentos al entrar a la pantalla
    LaunchedEffect(Unit) {
        viewModel.mostrarMedicamentos()
    }

    // Detectar el tema del dispositivo
    val isDark = isSystemInDarkTheme()

    // Colores dinámicos según el tema
    val fondo = if (isDark) Color(0xFF101418) else Color(0xFFF4F7FB)
    val titleColor = if (isDark) Color(0xFF778899) else Color(0xFF0F172A)
    val bannerColor = if (isDark) Color(0xFF101B3D) else Color(0xFF191970)
    val tarjetaColor = if (isDark) Color(0xFF252A35) else Color(0xFFE6E0EA)
    val colorBoton = if (isDark) Color(0xFF101B3D) else Color(0xFF191970)

    // Colores derivados para etiquetas, valores y enlace según el tema
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
        val seleccionado = uiState.medicamentoSeleccionado

        if (seleccionado != null) {
            // VISTA DE DETALLE DEL MEDICAMENTO SELECCIONADO Y PRODUCTOS APIFY
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Button(
                    onClick = { viewModel.regresarListado() },
                    colors = ButtonDefaults.buttonColors(containerColor = colorBoton)
                ) {
                    Text("← Regresar")
                }
            }

            Text(
                text = seleccionado.nombreGenerico,
                style = MaterialTheme.typography.headlineMedium,
                color = titleColor,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    DetalleMedicamento(seleccionado, label= labelColor, value= valueColor)
                }

                item {
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                    Text(
                        text = "Disponibilidad en Farmacia del Ahorro",
                        style = MaterialTheme.typography.titleLarge,
                        color = titleColor
                    )
                }

                when {
                    uiState.cargando -> {
                        item {
                            Box(
                                modifier = Modifier.fillMaxWidth().padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }

                    uiState.error != null -> {
                        item {
                            Text(
                                text = uiState.error ?: "Error al consultar farmacia",
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }

                    uiState.productos.isEmpty() -> {
                        item {
                            Text(
                                text = "No se encontraron ofertas en farmacia para este medicamento.",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }

                    else -> {
                        items(uiState.productos) { producto ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 6.dp),
                                shape = RoundedCornerShape(16.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = tarjetaColor
                                ),
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 4.dp
                                )
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                ) {
                                    //Imagen
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically
                                    ){
                                        Surface(
                                            modifier = Modifier.size(100.dp),
                                            shape = RoundedCornerShape(12.dp),
                                            color = Color.White
                                        ) {
                                            agregarImagen(producto.imagen)
                                        }
                                        Spacer(
                                            modifier = Modifier.width(16.dp)
                                        )

                                        Column(
                                            modifier = Modifier.weight(1f)
                                        ){
                                            Text(
                                                text = producto.nombreMedicamento,
                                                style = MaterialTheme.typography.titleLarge,
                                                fontWeight = FontWeight.Bold,
                                                color = titleColor
                                            )
                                            Spacer(
                                                modifier = Modifier.height(4.dp)
                                            )
                                            Text(
                                                text = "Presentacion: ${producto.nombreCompletoMedicameto}",
                                                style = MaterialTheme.typography.bodyMedium,
                                                color = titleColor.copy(alpha = 0.75f),
                                                maxLines = 2,
                                                overflow = TextOverflow.Ellipsis
                                            )
                                            Spacer(
                                                modifier = Modifier.height(8.dp)
                                            )
                                            Text(
                                                text = "Requiere receta ${validacion(producto.requiereReceta)}",
                                                style = MaterialTheme.typography.bodyMedium,
                                                color = titleColor.copy(alpha = 0.75f),
                                                maxLines = 2,
                                                overflow = TextOverflow.Ellipsis
                                            )

                                        }
                                    }
                                    HorizontalDivider(
                                        modifier = Modifier.padding(vertical = 12.dp),
                                        color = titleColor.copy(alpha = 0.12f)
                                    )
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column {
                                            Text(
                                                text = "Precio: $${producto.precio}",
                                                style = MaterialTheme.typography.bodyLarge,
                                                color = MaterialTheme.colorScheme.primary
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(4.dp))
                                        oneClickComprar(producto.enlace, colorBoton)
                                    }
                                }
                            }
                        }
                    }
                }
            }

        } else {
            // VISTA PRINCIPAL (BÚSQUEDA Y LISTADO GENERAL)
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
                    text = "Medicamentos",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineMedium
                )
            }

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Buscar medicamento") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {
                        if (nombre.isBlank()) {
                            viewModel.mostrarMedicamentos()
                        } else {
                            viewModel.buscarMedicamento(nombre)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.68f)
                        .height(45.dp),
                    shape = RoundedCornerShape(25.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorBoton,
                        contentColor = Color.White
                    ),
                    enabled = nombre.isNotBlank() && !uiState.cargando
                ) {
                    Text("Buscar")
                }
            }

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
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )

                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(uiState.medicamentos) { medicamento ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        viewModel.seleccionarMedicamento(medicamento)
                                        viewModel.buscarProductoPorNombre(medicamento.nombreGenerico)
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
                                        text = medicamento.nombreComercial,
                                        color = valueColor
                                    )

                                    Spacer(modifier = Modifier.height(4.dp))

                                    Text(
                                        text = "Grupo Farmacológico",
                                        style = MaterialTheme.typography.titleMedium,
                                        color = labelColor
                                    )
                                    Text(
                                        text = medicamento.grupoFarmacologico,
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
fun DetalleMedicamento(
    medicamento: com.example.myapplication.domain.model.Medicamento,
    label: Color,
    value: Color
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        InformacionCampo("Nombre genérico", medicamento.nombreGenerico, label,value)
        InformacionCampo("Nombre comercial", medicamento.nombreComercial,label,value)
        InformacionCampo("Grupo farmacológico", medicamento.grupoFarmacologico,label,value)
        InformacionCampo("Presentación", medicamento.presentacion,label,value)
        InformacionCampo("Mecanismo de acción", medicamento.mecanismoAccion,label,value)
        InformacionCampo("Vía de administración", medicamento.viaAdministracion,label,value)
        InformacionCampo("Indicaciones", medicamento.indicaciones,label,value)
        InformacionCampo("Contraindicaciones", medicamento.contraindicaciones,label,value)
    }
}

@Composable
fun InformacionCampo(
    titulo: String,
    contenido: String?,
    label: Color,
    value: Color

) {
    Column {
        Text(
            text = titulo,
            style = MaterialTheme.typography.titleSmall,
            color = label
        )
        Text(
            text = contenido ?: "No disponible",
            style = MaterialTheme.typography.bodyLarge,
            color = value
        )
    }
}


@Composable
fun agregarImagen(url: String){
    AsyncImage(
        model = url,
        contentDescription = "Imagen de Medicamentos",
        modifier = Modifier.size(150.dp),
        contentScale = ContentScale.Crop
    )
}

//Direccionamiento de enlace de compra

@Composable
fun oneClickComprar(url: String, colorBoton: Color){
    val uri = LocalUriHandler.current
    val colorBoton =
    Button(
        onClick = {
            uri.openUri(url)
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = colorBoton,
            contentColor = Color.White
        )
    ){
        Text("Comparar")
    }

}

//Validar si requiere Receta
@Composable
fun validacion(estado: Boolean): String{
    return if (estado) {
        stringResource(Res.string.Requiere_receta)
    } else {
        stringResource(Res.string.No_Requiere_Receta)
    }
}