package com.example.ecommerceappchallenge.domain.repository

import androidx.lifecycle.LiveData
import com.example.ecommerceappchallenge.data.model.CartItem
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    fun getShoppingCart(): LiveData<List<CartItem>>
    fun getCheckoutTotal(): LiveData<Double>
    suspend fun insertCartItem(cartItem: CartItem)
    suspend fun deleteCartItem(cartItem: CartItem)
    suspend fun deleteAll()
    suspend fun increaseQuantity(itemId: Int)
    suspend fun decreaseQuantity(itemId: Int)
    suspend fun getCartData(): List<CartItem>
    suspend fun getItemQuantity(itemId: Int): Int
}