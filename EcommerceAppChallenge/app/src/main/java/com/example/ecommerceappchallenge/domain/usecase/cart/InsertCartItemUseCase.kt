package com.example.ecommerceappchallenge.domain.usecase.cart

import com.example.ecommerceappchallenge.domain.repository.CartRepository
import com.example.ecommerceappchallenge.data.model.CartItem

class InsertCartItemUseCase(private val repository: CartRepository) {

    suspend fun execute(cartItem: CartItem) {
        return repository.insertCartItem(cartItem)
    }
}