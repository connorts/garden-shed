package com.example.ecommerceappchallenge.domain.usecase.cart

import com.example.ecommerceappchallenge.domain.repository.CartRepository
import com.example.ecommerceappchallenge.data.model.CartItem

class GetCartDataUseCase(private val repository: CartRepository) {

    suspend fun execute(): List<CartItem> {
        return repository.getCartData()
    }
}