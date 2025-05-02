package com.example.lbgassesment.presentation.state

import com.example.lbgassesment.domain.model.ProductItem

data class ProductListState(
    val isLoading: Boolean = false,
    val data: List<ProductItem>? = null,
    var error: String = ""
)
