
package com.sena.inventarioapp.feature.product.presentation.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sena.inventarioapp.feature.product.domain.model.Product
import java.text.NumberFormat
import java.util.Locale

@Composable
fun ProductCard(
    product: Product
) {
    val formato = NumberFormat.getCurrencyInstance(
        Locale.forLanguageTag("es-CO")
    )

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.titleMedium
            )

            Text("Precio: ${formato.format(product.priceCop)}")
            Text("Cantidad: ${product.quantity}")
        }
    }
}
