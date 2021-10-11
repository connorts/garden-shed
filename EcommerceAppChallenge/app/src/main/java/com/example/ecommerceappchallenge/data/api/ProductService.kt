package com.example.ecommerceappchallenge.data.api

import com.example.ecommerceappchallenge.data.model.Products
import com.example.ecommerceappchallenge.data.model.ProductsItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {
    @GET("products")
    suspend fun getProducts(): Response<Products>

    @GET("products/category/{category}")
    suspend fun getProductsByCategory(@Path(value = "category") category: String): Response<Products>

    @GET("products/{index}")
    suspend fun getSpecifiedProduct(@Path(value = "index") index: Int): Response<ProductsItem>
}