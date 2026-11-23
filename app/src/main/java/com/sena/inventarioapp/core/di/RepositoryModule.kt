
package com.sena.inventarioapp.core.di

import com.sena.inventarioapp.feature.product.data.repository.ProductRepositoryImpl
import com.sena.inventarioapp.feature.product.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductRepository(
        implementation: ProductRepositoryImpl
    ): ProductRepository
}
