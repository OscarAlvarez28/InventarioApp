
package com.sena.inventarioapp.feature.product.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sena.inventarioapp.feature.product.presentation.section.ProductListSection
import com.sena.inventarioapp.feature.product.presentation.composables.ProductDeleteDialog
import com.sena.inventarioapp.feature.product.presentation.viewmodel.ProductListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    vm: ProductListViewModel,
    onCreate: () -> Unit,
    onEdit: (String) -> Unit
) {
    val ui by vm.ui.collectAsStateWithLifecycle()

    var pendingDeleteId by remember {
        mutableStateOf<String?>(null)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Inventario") },
                actions = {
                    TextButton(
                        onClick = { vm.sync() },
                        enabled = !ui.isSyncing
                    ) {
                        Text("Sincronizar")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onCreate) {
                Text("+")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            if (ui.isSyncing) {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth()
                )
            }

            ui.message?.let {
                Text(
                    text = it,
                    modifier = Modifier.padding(8.dp)
                )
            }

            ProductListSection(
                products = ui.products,
                onEdit = onEdit,
                onDelete = { id ->
                    pendingDeleteId = id
                }
            )
        }
    }

    if (pendingDeleteId != null) {
        ProductDeleteDialog(
            onConfirm = {
                pendingDeleteId?.let { id ->
                    vm.delete(id)
                }
                pendingDeleteId = null
            },
            onDismiss = {
                pendingDeleteId = null
            }
        )
    }
}
