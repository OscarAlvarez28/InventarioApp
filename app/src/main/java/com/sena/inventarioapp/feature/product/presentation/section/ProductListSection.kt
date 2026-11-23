
package com.sena.inventarioapp.feature.product.presentation.section

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sena.inventarioapp.feature.product.domain.model.Product
import com.sena.inventarioapp.feature.product.presentation.composables.ProductCard

@Composable
fun ProductListSection(
    products: List<Product>
) {

    if (products.isEmpty()) {
        Text(
            text = "Todavía no hay productos registrados.",
            modifier = Modifier.padding(16.dp)
        )
    } else {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(products, key = { it.id }) { product ->
                ProductCard(product = product)
            }
        }
    }
}
