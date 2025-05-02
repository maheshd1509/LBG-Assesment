package com.example.lbgassesment.presentation.state

import com.example.lbgassesment.domain.model.ProductDetail

data class ProductDetailState(val isLoading : Boolean = false,
                              val data : ProductDetail? = null,
                              var error : String ="")
