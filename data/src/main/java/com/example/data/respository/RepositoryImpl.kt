package com.example.lbgassesment.data.respository

import com.example.lbgassesment.data.model.toProductDetail
import com.example.lbgassesment.data.model.toProductList
import com.example.lbgassesment.data.netwotk.ApiService
import com.example.lbgassesment.data.model.ProductDetail
import com.example.lbgassesment.data.model.ProductItem
import com.example.lbgassesment.data.repository.Repository
import javax.inject.Inject


class RepositoryImpl @Inject constructor(private val apiService: ApiService) : Repository {

    override suspend fun getProductList(): List<ProductItem> {
        return apiService.getAllProductListAPI().map { it.toProductList() }
    }

    override suspend fun getProductDetail(id: String): ProductDetail {
        return apiService.getProductDetailsAPI(id).toProductDetail()
    }

}