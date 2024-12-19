package com.elidacaceres.tpfinal

import HomeOptionsViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeOptions(
    viewModel: HomeOptionsViewModel,
    onNavigateToCurrentChat: () -> Unit,
    onNavigateToPreviousChats: () -> Unit,
    onNavigateToWelcome: () -> Unit
) {
    val logoutResult by viewModel.logoutResult.observeAsState()
    val errorMessage by viewModel.errorMessage.observeAsState()

    // Navega a la pantalla de bienvenida si el logout es exitoso
    if (logoutResult == true) {
        onNavigateToWelcome()
    }

    var showDialog by remember { mutableStateOf(false) }
    // Confirmación de cierre de sesión
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Cerrar Sesión") },
            text = { Text("¿Estás seguro de que deseas cerrar sesión?") },
            confirmButton = {
                Button(onClick = {
                    showDialog = false
                    viewModel.logout() // Llamar a la función de cierre de sesión
                }) {
                    Text("Aceptar")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Botón para cerrar sesión en la esquina superior derecha
        IconButton(
            onClick = { showDialog = true }, // Mostrar el cuadro de diálogo de confirmación
            modifier = Modifier
                .align(Alignment.TopEnd) // Posicionar en la esquina superior derecha
                .padding(8.dp)
                .size(50.dp) // Tamaño del ícono
        ) {
            Icon(
                imageVector = Icons.Filled.ExitToApp,
                contentDescription = "Cerrar Sesión",
                tint = Color.Red, // Color del ícono
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // Logo
            Image(
                painter = painterResource(id = R.drawable.logo_2),
                contentDescription = "Logo de la app",
                modifier = Modifier
                    .size(120.dp)
                    .padding(bottom = 16.dp),
                contentScale = ContentScale.Fit
            )

            // Texto de selección de opción
            Text(
                text = "Selecciona una opción",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Botón para iniciar un nuevo chat
            Button(
                onClick = onNavigateToCurrentChat,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF800000),
                    contentColor = Color.White
                )
            ) {
                Text("Nuevo Chat")
            }

            // Botón para ver chats anteriores
            Button(
                onClick = onNavigateToPreviousChats,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF800000),
                    contentColor = Color.White
                )
            ) {
                Text("Ver Chats Anteriores")
            }
        }
    }
}


