package com.example.ecommerceappchallenge.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.ecommerceappchallenge.data.model.CartItem
import com.example.ecommerceappchallenge.data.model.Rating

class FakeCartRepository:CartRepository {
    private val cart = mutableListOf<CartItem>()

    init {
        cart.add(CartItem("category1","description1",0,"image1",1.11, Rating(1.1,1),"title1",1))
        cart.add(CartItem("category2","description2",1,"image2",2.22, Rating(2.2,2),"title2",2))
    }

    override fun getShoppingCart(): LiveData<List<CartItem>> {
        return liveData { emit(cart) }
    }

    override fun getCheckoutTotal(): LiveData<Double> {
        return liveData { emit(3.33) }
    }

    override suspend fun insertCartItem(cartItem: CartItem) {
        cart.add(cartItem)
    }

    override suspend fun deleteCartItem(cartItem: CartItem) {
        cart.remove(cartItem)
    }

    override suspend fun deleteAll() {
        cart.clear()
    }

    override suspend fun increaseQuantity(itemId: Int) {
        cart[itemId].quantity++
    }

    override suspend fun decreaseQuantity(itemId: Int) {
        cart[itemId].quantity--
    }

    override suspend fun getCartData(): List<CartItem> {
        return cart
    }

    override suspend fun getItemQuantity(itemId: Int): Int {
        return cart[itemId].quantity
    }
}