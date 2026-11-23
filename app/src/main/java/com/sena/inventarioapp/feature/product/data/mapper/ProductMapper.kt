
package com.sena.inventarioapp.feature.product.data.mapper

import com.sena.inventarioapp.feature.product.data.local.ProductEntity
import com.sena.inventarioapp.feature.product.domain.model.Product

fun ProductEntity.toDomain(): Product {
    return Product(
        id = id,
        name = name,
        description = description,
        priceCop = priceCop,
        quantity = quantity,
        updatedAt = updatedAt
    )
}

fun Product.toEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        name = name,
        description = description,
        priceCop = priceCop,
        quantity = quantity,
        updatedAt = updatedAt,
        pending = true,
        isDeleted = false
    )
}
