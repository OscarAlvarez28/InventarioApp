
package com.sena.inventarioapp

import dagger.hilt.android.AndroidEntryPoint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sena.inventarioapp.core.navigation.AppNavigation
import com.sena.inventarioapp.ui.theme.InventarioAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            InventarioAppTheme {
                AppNavigation()
            }
        }
    }
}
