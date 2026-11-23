
package com.sena.inventarioapp.feature.product.presentation.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sena.inventarioapp.feature.product.presentation.section.ProductFormSection
import com.sena.inventarioapp.feature.product.presentation.viewmodel.ProductFormViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductFormScreen(
    vm: ProductFormViewModel,
    productId: String?,
    onBack: () -> Unit
) {
    val ui by vm.ui.collectAsStateWithLifecycle()

    LaunchedEffect(productId) {
        vm.load(productId)
    }

    LaunchedEffect(ui.saved) {
        if (ui.saved) onBack()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        if (productId == null)
                            "Registrar producto"
                        else
                            "Editar producto"
                    )
                },
                navigationIcon = {
                    TextButton(onClick = onBack) {
                        Text("Volver")
                    }
                }
            )
        }
    ) { padding ->
        androidx.compose.foundation.layout.Box(
            modifier = Modifier.padding(padding)
        ) {
            ProductFormSection(
                ui = ui,
                onName = vm::updateName,
                onDescription = vm::updateDescription,
                onPrice = vm::updatePrice,
                onQuantity = vm::updateQuantity,
                onSave = vm::save
            )
        }
    }
}
