package com.example.ecommerceappchallenge.domain.usecase.offers

import com.example.ecommerceappchallenge.data.model.ProductsItem
import com.example.ecommerceappchallenge.domain.repository.OffersRepository

class SaveDailyDealUseCase(private val repository: OffersRepository) {
    suspend fun execute(productsItem: ProductsItem) {
        return repository.insertOffer(productsItem)
    }
}