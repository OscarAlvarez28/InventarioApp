
package com.sena.inventarioapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.sena.inventarioapp.feature.product.presentation.screen.ProductListScreen
import com.sena.inventarioapp.feature.product.presentation.screen.ProductFormScreen
import com.sena.inventarioapp.feature.product.presentation.viewmodel.ProductListViewModel
import kotlinx.serialization.Serializable

@Serializable
data object ProductListRoute : NavKey

@Serializable
data object ProductFormRoute : NavKey

@Composable
fun AppNavigation() {

    val backStack = rememberNavBackStack(ProductListRoute)

    NavDisplay(
        backStack = backStack,

        onBack = {
            if (backStack.size > 1) {
                backStack.removeLastOrNull()
            }
        },

        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),

        entryProvider = entryProvider {

            entry<ProductListRoute> {

                val vm: ProductListViewModel = hiltViewModel()

                ProductListScreen(
                    vm = vm,
                    onCreate = {
                        backStack.add(ProductFormRoute)
                    }
                )
            }

            entry<ProductFormRoute> {
                ProductFormScreen(
                    onBack = {
                        backStack.removeLastOrNull()
                    }
                )
            }
        }
    )
}
