package com.example.ecommerceappchallenge.domain.usecase.cart

import com.example.ecommerceappchallenge.domain.repository.CartRepository

class DecreaseItemQuantityUseCase(private val repository: CartRepository) {

    suspend fun execute(itemId: Int) {
        return repository.decreaseQuantity(itemId)
    }
}