
package com.sena.inventarioapp.feature.product.presentation.composables

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun ProductDeleteDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Eliminar producto") },
        text = {
            Text("¿Seguro que deseas eliminar este producto?")
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Eliminar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}
