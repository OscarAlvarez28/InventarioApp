
package com.sena.inventarioapp.feature.product.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sena.inventarioapp.feature.product.domain.model.Product
import com.sena.inventarioapp.feature.product.domain.repository.ProductRepository
import com.sena.inventarioapp.feature.product.domain.usecase.SaveProductUseCase
import com.sena.inventarioapp.feature.product.presentation.state.ProductFormUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ProductFormViewModel @Inject constructor(
    private val repository: ProductRepository,
    private val saveProduct: SaveProductUseCase
) : ViewModel() {

    private val _ui = MutableStateFlow(ProductFormUiState())
    val ui: StateFlow<ProductFormUiState> = _ui

    private var editingId: String? = null
    private var loaded = false

    fun load(id: String?) {
        if (loaded) return
        loaded = true
        editingId = id

        if (id == null) return

        viewModelScope.launch {
            val product = repository.findById(id) ?: return@launch

            _ui.value = ProductFormUiState(
                name = product.name,
                description = product.description,
                price = product.priceCop.toString(),
                quantity = product.quantity.toString()
            )
        }
    }

    fun updateName(value: String) {
        _ui.value = _ui.value.copy(name = value)
    }

    fun updateDescription(value: String) {
        _ui.value = _ui.value.copy(description = value)
    }

    fun updatePrice(value: String) {
        _ui.value = _ui.value.copy(price = value)
    }

    fun updateQuantity(value: String) {
        _ui.value = _ui.value.copy(quantity = value)
    }

    fun save() {
        viewModelScope.launch {
            val current = _ui.value

            val price = current.price.toLongOrNull()
            val quantity = current.quantity.toIntOrNull()

            if (price == null || quantity == null) {
                _ui.value = current.copy(
                    error = "Precio y cantidad deben ser números"
                )
                return@launch
            }

            _ui.value = current.copy(
                saving = true,
                error = null
            )

            try {
                val product = Product(
                    id = editingId ?: UUID.randomUUID().toString(),
                    name = current.name.trim(),
                    description = current.description.trim(),
                    priceCop = price,
                    quantity = quantity
                )

                saveProduct(product)

                _ui.value = _ui.value.copy(
                    saving = false,
                    saved = true
                )

            } catch (e: Exception) {
                _ui.value = _ui.value.copy(
                    saving = false,
                    error = e.message ?: "Error al guardar"
                )
            }
        }
    }
}
