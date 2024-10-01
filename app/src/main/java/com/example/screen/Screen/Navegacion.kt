package com.example.screen.Screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.screen.ContactViewModel

@Composable
fun Navegacion() {
    val navController = rememberNavController()

    // Crear una sola instancia del ViewModel que será compartida
    val viewModelStoreOwner = LocalViewModelStoreOwner.current
    val contactViewModel = viewModel<ContactViewModel>(viewModelStoreOwner!!)

    NavHost(navController = navController, startDestination = "screen-a") {
        composable("screen-a") {
            ScreenA(navController = navController, contactViewModel = contactViewModel)
        }
        composable("screen-b") {
            ScreenB(navController = navController, contactViewModel = contactViewModel)
        }
        composable(
            route = "screen-edit/{index}",
            arguments = listOf(navArgument("index") { type = NavType.IntType })
        ) { backStackEntry ->
            val index = backStackEntry.arguments?.getInt("index") ?: 0
            ScreenEdit(navController = navController, contactViewModel = contactViewModel, index = index)
        }
    }
}
