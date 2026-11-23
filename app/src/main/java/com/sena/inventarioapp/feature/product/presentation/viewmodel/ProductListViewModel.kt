
package com.sena.inventarioapp.feature.product.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sena.inventarioapp.feature.product.domain.repository.ProductRepository
import com.sena.inventarioapp.feature.product.presentation.state.ProductListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _ui = MutableStateFlow(ProductListUiState())

    val ui: StateFlow<ProductListUiState> = _ui

    init {
        viewModelScope.launch {
            repository.observeProducts().collect { products ->
                _ui.value = _ui.value.copy(
                    products = products
                )
            }
        }
    }

    fun delete(id: String) {
        viewModelScope.launch {
            repository.delete(id)
        }
    }
}
