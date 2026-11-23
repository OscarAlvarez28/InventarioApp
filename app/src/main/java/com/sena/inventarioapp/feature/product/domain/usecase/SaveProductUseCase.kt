
package com.sena.inventarioapp.feature.product.domain.usecase

import com.sena.inventarioapp.feature.product.domain.model.Product
import com.sena.inventarioapp.feature.product.domain.repository.ProductRepository
import javax.inject.Inject

class SaveProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(product: Product) {
        require(product.name.isNotBlank()) {
            "El nombre es obligatorio"
        }
        require(product.name.length <= 80) {
            "Máximo 80 caracteres"
        }
        require(product.description.length <= 500) {
            "Descripción demasiado larga"
        }
        require(product.priceCop > 0) {
            "El precio debe ser positivo"
        }
        require(product.quantity >= 0) {
            "La cantidad no puede ser negativa"
        }

        repository.save(product)
    }
}
