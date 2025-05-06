package com.example.lbgassesment.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lbgassesment.presentation.screens.ProductDetailsScreen
import com.example.lbgassesment.presentation.screens.ProductListScreen

@Composable
fun ScreenNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Screen.MainScreen.ProductList.screen) {
        composable(route = Screen.MainScreen.ProductList.screen) {
            ProductListScreen(navController)
        }
        composable(
            route = Screen.MainScreen.ProductDetails.screen,
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")
            ProductDetailsScreen(navController, id)
        }
    }
}