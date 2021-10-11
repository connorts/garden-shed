package com.example.ecommerceappchallenge.domain.usecase.cart

import androidx.lifecycle.LiveData
import com.example.ecommerceappchallenge.domain.repository.CartRepository
import com.example.ecommerceappchallenge.data.model.CartItem
import kotlinx.coroutines.flow.Flow

class GetShoppingCartUseCase(private val repository: CartRepository) {

    fun execute(): LiveData<List<CartItem>> {
        return repository.getShoppingCart()
    }
}