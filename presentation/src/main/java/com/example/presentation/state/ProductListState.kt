package com.example.lbgassesment.presentation.state

import com.example.lbgassesment.data.model.ProductItem

data class ProductListState(
    val isLoading: Boolean = false,
    val data: List<ProductItem>? = null,
    var error: String = ""
)
