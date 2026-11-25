
package com.example.myapplication.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import myapplication.shared.generated.resources.Res
import myapplication.shared.generated.resources.logo
import org.jetbrains.compose.resources.painterResource

@Composable
fun vistaScreen(
    onNavigationsearch:() -> Unit={},
    onNavigationSintomas:() -> Unit={}
){

    // Detectar el tema del dispositivo
    val isDark = isSystemInDarkTheme()

    // Colores dinámicos según el tema
    val fondo = if (isDark) {
        Color(0xFF101418)
    } else {
        Color(0xFFF4F7FB)
    }

    val titleColor = if (isDark) {
        Color(0xFFEAF0F6)
    } else {
        Color(0xFF0F172A)
    }

    val bannerColor = if (isDark){
        Color(0xFF101B3D)
    } else {
        Color(0xFF191970)
    }

    val tarjetaColor = if (isDark) {
        Color(0xFF252A35)
    } else {
        Color(0xFFE6E0EA)
    }

    //Colores de bottones
    val colorBoton = if (isDark){
        Color(0xFF101B3D)
    } else {
        Color(0xFF191970)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(fondo)
            .statusBarsPadding()
            .padding(
                horizontal = 20.dp,
                vertical = 24.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        // Encabezado
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(bannerColor, shape = RoundedCornerShape(8.dp))
                .padding(
                    horizontal = 20.dp,
                    vertical = 18.dp
                )
        ) {
            Text(
                text = "MediNova",
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium
            )
        }

        // Tarjeta principal
        Card(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 10.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = tarjetaColor
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                // Imagen del logo
                Image(
                    painter = painterResource(Res.drawable.logo),
                    contentDescription = "Logo de MediNova",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .background(Color.White),
                    contentScale = ContentScale.Fit
                )

                // Descripción
                Text(
                    text = "Una nueva forma de consultar medicamentos",
                    color = titleColor,
                    modifier = Modifier.padding(
                        horizontal = 10.dp,
                        vertical = 16.dp
                    ),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

        // Botones principales
        ordenarButton(colorBoton,onNavigationsearch = onNavigationsearch, onNavigationSintomas = onNavigationSintomas)
    }
}

// Botón reutilizable
@Composable
fun botonPrincipal(texto: String, colorBoton: Color, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(0.80f)
            .height(50.dp),
        shape = RoundedCornerShape(25.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorBoton,
            contentColor = Color.White
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 0.dp,
            pressedElevation = 2.dp
        )
    ) {

        Text(
            text = texto,
            color = Color.White,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

// Organización de los botones
@Composable
fun ordenarButton(colorBoton: Color,
                  onNavigationsearch: () -> Unit,
                  onNavigationSintomas: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {

        botonPrincipal(
            texto = "Buscar Medicamento",
            colorBoton = colorBoton,
            onClick = onNavigationsearch
        )

        botonPrincipal(
            texto = "Presentas algún síntoma",
            colorBoton = colorBoton,
            onClick = onNavigationSintomas
        )
    }
}

// Vista previa
@Preview(
    showBackground = true,
    name = "MediNova"
)
@Composable
fun preview() {
    vistaScreen()
}
