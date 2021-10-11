package com.example.ecommerceappchallenge.data.repository.dataSourceImpl

import com.example.ecommerceappchallenge.data.api.ProductService
import com.example.ecommerceappchallenge.data.model.Products
import com.example.ecommerceappchallenge.data.model.ProductsItem
import com.example.ecommerceappchallenge.data.repository.dataSource.ProductsRemoteDataSource
import retrofit2.Response

class ProductsRemoteDataSourceImpl(private val apiService: ProductService): ProductsRemoteDataSource {
    override suspend fun getProducts(): Response<Products> {
        return apiService.getProducts()
    }

    override suspend fun getProductsByCategory(category: String): Response<Products> {
        return apiService.getProductsByCategory(category)
    }

    override suspend fun getSpecifiedProduct(index: Int): Response<ProductsItem> {
        return apiService.getSpecifiedProduct(index)
    }
}