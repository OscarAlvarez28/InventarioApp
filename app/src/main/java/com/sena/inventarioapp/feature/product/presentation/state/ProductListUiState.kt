
package com.sena.inventarioapp.feature.product.presentation.state

import com.sena.inventarioapp.feature.product.domain.model.Product

data class ProductListUiState(
    val products: List<Product> = emptyList(),
    val isSyncing: Boolean = false,
    val message: String? = null
)
