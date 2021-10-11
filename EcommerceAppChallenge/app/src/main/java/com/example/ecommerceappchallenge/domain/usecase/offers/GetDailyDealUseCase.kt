package com.example.ecommerceappchallenge.domain.usecase.offers

import com.example.ecommerceappchallenge.data.model.ProductsItem
import com.example.ecommerceappchallenge.data.util.Resource
import com.example.ecommerceappchallenge.domain.repository.ProductRepository

class GetDailyDealUseCase(private val repository: ProductRepository) {
    suspend fun execute(): Resource<ProductsItem> {
        return repository.getRandomProduct()
    }
}