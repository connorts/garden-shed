package com.example.ecommerceappchallenge.domain.usecase.cart

import com.example.ecommerceappchallenge.domain.repository.CartRepository

class DeleteCartUseCase(private val repository: CartRepository) {

    suspend fun execute() {
        return repository.deleteAll()
    }
}