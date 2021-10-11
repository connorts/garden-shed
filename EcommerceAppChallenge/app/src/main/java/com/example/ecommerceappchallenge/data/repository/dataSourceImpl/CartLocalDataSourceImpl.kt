package com.example.ecommerceappchallenge.data.repository.dataSourceImpl

import androidx.lifecycle.LiveData
import com.example.ecommerceappchallenge.data.local.CartItemDAO
import com.example.ecommerceappchallenge.data.model.CartItem
import com.example.ecommerceappchallenge.data.repository.dataSource.CartLocalDataSource
import kotlinx.coroutines.flow.Flow

class CartLocalDataSourceImpl(private val dao: CartItemDAO): CartLocalDataSource {
    override fun getShoppingCart(): LiveData<List<CartItem>> {
        return dao.getShoppingCart()
    }

    override fun getCheckoutTotal(): LiveData<Double> {
        return dao.getCheckoutTotal()
    }

    override suspend fun insertCartItem(cartItem: CartItem) {
        return dao.insertCartItem(cartItem)
    }

    override suspend fun deleteCartItem(cartItem: CartItem) {
        return dao.deleteCartItem(cartItem)
    }

    override suspend fun deleteAll() {
        return dao.deleteAll()
    }

    override suspend fun increaseQuantity(itemId: Int) {
        return dao.increaseQuantity(itemId)
    }

    override suspend fun decreaseQuantity(itemId: Int) {
        return dao.decreaseQuantity(itemId)
    }

    override suspend fun getCartData(): List<CartItem> {
        return dao.getCartData()
    }

    override suspend fun getItemQuantity(itemId: Int): Int {
        return dao.getItemQuantity(itemId)
    }
}