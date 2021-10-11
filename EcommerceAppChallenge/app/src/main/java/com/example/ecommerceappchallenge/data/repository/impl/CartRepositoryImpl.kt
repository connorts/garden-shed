package com.example.ecommerceappchallenge.data.repository.impl

import androidx.lifecycle.LiveData
import com.example.ecommerceappchallenge.data.local.CartItemDAO
import com.example.ecommerceappchallenge.data.model.CartItem
import com.example.ecommerceappchallenge.data.repository.dataSource.CartLocalDataSource
import com.example.ecommerceappchallenge.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow

class CartRepositoryImpl(private val cartLocalDataSource: CartLocalDataSource): CartRepository {
    override fun getShoppingCart(): LiveData<List<CartItem>> {
        return cartLocalDataSource.getShoppingCart()
    }

    override fun getCheckoutTotal(): LiveData<Double> {
        return cartLocalDataSource.getCheckoutTotal()
    }

    override suspend fun insertCartItem(cartItem: CartItem) {
        return cartLocalDataSource.insertCartItem(cartItem)
    }

    override suspend fun deleteCartItem(cartItem: CartItem) {
        return cartLocalDataSource.deleteCartItem(cartItem)
    }

    override suspend fun deleteAll() {
        return cartLocalDataSource.deleteAll()
    }

    override suspend fun increaseQuantity(itemId: Int) {
        return cartLocalDataSource.increaseQuantity(itemId)
    }

    override suspend fun decreaseQuantity(itemId: Int) {
        return cartLocalDataSource.decreaseQuantity(itemId)
    }

    override suspend fun getCartData(): List<CartItem> {
        return cartLocalDataSource.getCartData()
    }

    override suspend fun getItemQuantity(itemId: Int): Int {
        return cartLocalDataSource.getItemQuantity(itemId)
    }
}