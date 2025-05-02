package com.example.lbgassesment.domain.repository

import com.example.lbgassesment.domain.model.ProductDetail
import com.example.lbgassesment.domain.model.ProductItem

interface Repository {

    suspend fun getProductList() : List<ProductItem>

    suspend fun getProductDetail(id : String) : ProductDetail

}