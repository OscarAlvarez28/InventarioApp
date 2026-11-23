
package com.sena.inventarioapp.feature.product.data.repository

import com.sena.inventarioapp.feature.product.data.local.ProductDao
import com.sena.inventarioapp.feature.product.data.mapper.toDomain
import com.sena.inventarioapp.feature.product.data.mapper.toEntity
import com.sena.inventarioapp.feature.product.domain.model.Product
import com.sena.inventarioapp.feature.product.domain.repository.ProductRepository
import com.sena.inventarioapp.feature.product.domain.repository.SyncReport
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val dao: ProductDao
) : ProductRepository {

    override fun observeProducts(): Flow<List<Product>> {
        return dao.observeVisible().map { products ->
            products.map { it.toDomain() }
        }
    }

    override suspend fun findById(id: String): Product? {
        return dao.find(id)
            ?.takeUnless { it.isDeleted }
            ?.toDomain()
    }

    override suspend fun save(product: Product) {
        dao.upsert(
            product.copy(
                updatedAt = System.currentTimeMillis()
            ).toEntity()
        )
    }

    override suspend fun delete(id: String) {
        val product = dao.find(id) ?: return

        dao.upsert(
            product.copy(
                isDeleted = true,
                pending = true,
                updatedAt = System.currentTimeMillis()
            )
        )
    }

    override suspend fun syncNow(): SyncReport {
        // Firebase se implementará después.
        throw UnsupportedOperationException(
            "Sincronización todavía no configurada"
        )
    }
}
