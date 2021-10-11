package com.example.ecommerceappchallenge.data.repository.dataSource

import com.example.ecommerceappchallenge.data.model.Products
import com.example.ecommerceappchallenge.data.model.ProductsItem
import com.example.ecommerceappchallenge.data.util.Resource
import retrofit2.Response

interface ProductsRemoteDataSource {

    suspend fun getProducts():Response<Products>
    suspend fun getProductsByCategory(category: String): Response<Products>
    suspend fun getSpecifiedProduct(index: Int): Response<ProductsItem>
}