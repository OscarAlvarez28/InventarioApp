
package com.sena.inventarioapp.feature.product.presentation.section

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.sena.inventarioapp.feature.product.presentation.composables.ProductFormField
import com.sena.inventarioapp.feature.product.presentation.state.ProductFormUiState

@Composable
fun ProductFormSection(
    ui: ProductFormUiState,
    onName: (String) -> Unit,
    onDescription: (String) -> Unit,
    onPrice: (String) -> Unit,
    onQuantity: (String) -> Unit,
    onSave: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ProductFormField("Nombre", ui.name, onName)

        ProductFormField(
            "Descripción",
            ui.description,
            onDescription
        )

        ProductFormField(
            "Precio COP",
            ui.price,
            onPrice,
            KeyboardType.Number
        )

        ProductFormField(
            "Cantidad",
            ui.quantity,
            onQuantity,
            KeyboardType.Number
        )

        ui.error?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error
            )
        }

        Button(
            onClick = onSave,
            enabled = !ui.saving,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                if (ui.saving) "Guardando..." else "Guardar producto"
            )
        }
    }
}
