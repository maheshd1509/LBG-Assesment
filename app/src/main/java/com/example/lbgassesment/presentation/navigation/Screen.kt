package com.example.lbgassesment.presentation.navigation

import androidx.navigation.NavController


sealed class Screen(val screen: String) {
    object MainScreen : Screen("mainScreen") {

        object ProductList : Screen("productList") {
            fun NavController.toProductList() = navigate("productList")
        }

        object ProductDetails : Screen("productDetails/{id}") {
            fun NavController.toProductDetails(id : Int) = navigate("productDetails/$id")

        }
    }
}
