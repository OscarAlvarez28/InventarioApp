
package com.sena.inventarioapp.feature.product.presentation.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sena.inventarioapp.feature.product.presentation.section.ProductFormSection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductFormScreen(
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registrar producto") },
                navigationIcon = {
                    TextButton(onClick = onBack) {
                        Text("Volver")
                    }
                }
            )
        }
    ) { padding ->
        Surface(
            modifier = Modifier.padding(padding)
        ) {
            ProductFormSection()
        }
    }
}
