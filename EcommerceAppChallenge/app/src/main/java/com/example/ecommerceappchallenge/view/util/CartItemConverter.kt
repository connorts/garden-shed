package com.example.ecommerceappchallenge.view.util

import com.example.ecommerceappchallenge.data.model.CartItem
import com.example.ecommerceappchallenge.data.model.ProductsItem

class CartItemConverter {
    companion object {

        fun productToCartItem(productsItem: ProductsItem): CartItem {
            return CartItem(
                productsItem.category,
                productsItem.description,
                productsItem.id,
                productsItem.image,
                productsItem.price,
                productsItem.rating,
                productsItem.title,
                1
            )
        }
    }
}