package com.example.ecommerceappchallenge.domain.repository

import com.example.ecommerceappchallenge.data.model.Products
import com.example.ecommerceappchallenge.data.model.ProductsItem
import com.example.ecommerceappchallenge.data.util.Resource
import retrofit2.Response

interface ProductRepository {
    suspend fun getProducts(): Resource<Products>
    suspend fun getProductsByCategory(category: String):Resource<Products>
    suspend fun getRandomProduct(): Resource<ProductsItem>
}