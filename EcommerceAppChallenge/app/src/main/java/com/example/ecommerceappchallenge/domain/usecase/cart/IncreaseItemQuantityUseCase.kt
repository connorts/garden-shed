package com.example.ecommerceappchallenge.domain.usecase.cart

import com.example.ecommerceappchallenge.domain.repository.CartRepository

class IncreaseItemQuantityUseCase(private val repository: CartRepository) {

    suspend fun execute(itemId: Int) {
        return repository.increaseQuantity(itemId)
    }
}