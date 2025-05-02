package com.example.lbgassesment.data.respository

import com.example.lbgassesment.core.common.toProductDetail
import com.example.lbgassesment.core.common.toProductList
import com.example.lbgassesment.data.netwotk.ApiService
import com.example.lbgassesment.domain.model.ProductDetail
import com.example.lbgassesment.domain.model.ProductItem
import com.example.lbgassesment.domain.repository.Repository
import javax.inject.Inject


class RepositoryImpl @Inject constructor(private val apiService: ApiService) : Repository {

    override suspend fun getProductList(): List<ProductItem> {
        return apiService.getAllProductListAPI().map { it.toProductList() }
    }

    override suspend fun getProductDetail(id: String): ProductDetail {
        return apiService.getProductDetailsAPI(id).toProductDetail()
    }

}