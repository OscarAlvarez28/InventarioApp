
package com.sena.inventarioapp.feature.product.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sena.inventarioapp.feature.product.domain.repository.ProductRepository
import com.sena.inventarioapp.feature.product.presentation.state.ProductListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
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
            repository.observeProducts()
                .catch { error ->
                    _ui.value = _ui.value.copy(
                        message = error.message
                    )
                }
                .collect { products ->
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

    fun sync() {
        if (_ui.value.isSyncing) return

        viewModelScope.launch {
            _ui.value = _ui.value.copy(
                isSyncing = true,
                message = null
            )

            try {
                val report = repository.syncNow()

                _ui.value = _ui.value.copy(
                    message = "Sincronizado: ${report.uploaded} enviados, " +
                            "${report.downloaded} recibidos"
                )
            } catch (e: Exception) {
                _ui.value = _ui.value.copy(
                    message = "Error al sincronizar: " +
                            (e.message ?: "Revisa la conexión")
                )
            } finally {
                _ui.value = _ui.value.copy(
                    isSyncing = false
                )
            }
        }
    }
}
