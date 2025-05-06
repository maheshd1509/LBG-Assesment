package com.example.lbgassesment.data.repository

import com.example.lbgassesment.data.model.ProductDetail
import com.example.lbgassesment.data.model.ProductItem

interface Repository {

    suspend fun getProductList() : List<ProductItem>

    suspend fun getProductDetail(id : String) : ProductDetail

}