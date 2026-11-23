
package com.sena.inventarioapp.feature.product.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sena.inventarioapp.feature.product.presentation.section.ProductListSection
import com.sena.inventarioapp.feature.product.presentation.viewmodel.ProductListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    vm: ProductListViewModel,
    onCreate: () -> Unit
) {
    val ui by vm.ui.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Inventario") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onCreate
            ) {
                Text("+")
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            ProductListSection(
                products = ui.products
            )
        }
    }
}
