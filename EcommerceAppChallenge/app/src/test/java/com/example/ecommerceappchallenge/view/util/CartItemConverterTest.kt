package com.example.ecommerceappchallenge.view.util

import com.example.ecommerceappchallenge.data.model.CartItem
import com.example.ecommerceappchallenge.data.model.ProductsItem
import com.example.ecommerceappchallenge.data.model.Rating
import com.google.common.truth.Truth
import org.junit.jupiter.api.Test

class CartItemConverterTest {

    @Test
    fun productToCartItem_GivenProductsItem_ReturnsCartItem() {
        val product = ProductsItem("cat1","dec1",1,"img1",1.11, Rating(2.0,1),"tit1")
        val result = CartItemConverter.productToCartItem(product)
        val cartItem = CartItem("cat1","dec1",1,"img1",1.11, Rating(2.0,1),"tit1",1)
        Truth.assertThat(result).isEqualTo(cartItem)
    }
}