package com.example.ecommerceappchallenge.domain.usecase.products

import com.example.ecommerceappchallenge.data.model.Products
import com.example.ecommerceappchallenge.data.util.Resource
import com.example.ecommerceappchallenge.domain.repository.ProductRepository

class GetProductsUseCase(private val repository: ProductRepository) {
    suspend fun execute(): Resource<Products> {
        return repository.getProducts()
    }
}