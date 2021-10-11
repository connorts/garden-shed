package com.example.ecommerceappchallenge.domain.usecase.products

import com.example.ecommerceappchallenge.data.model.Products
import com.example.ecommerceappchallenge.data.util.Resource
import com.example.ecommerceappchallenge.domain.repository.ProductRepository
import retrofit2.Response

class GetProductsByCategoryUseCase(private val repository: ProductRepository) {
    suspend fun execute(category: String): Resource<Products> {
        return repository.getProductsByCategory(category)
    }
}