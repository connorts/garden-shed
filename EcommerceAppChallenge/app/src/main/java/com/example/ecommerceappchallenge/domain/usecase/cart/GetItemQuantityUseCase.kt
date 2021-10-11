package com.example.ecommerceappchallenge.domain.usecase.cart

import com.example.ecommerceappchallenge.domain.repository.CartRepository

class GetItemQuantityUseCase(private val repository: CartRepository) {

    suspend fun execute(itemId: Int): Int {
        return repository.getItemQuantity(itemId)
    }
}