package com.example.ecommerceappchallenge.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ecommerceappchallenge.data.model.CartItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CartItemDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(cartItem: CartItem)

    @Delete
    suspend fun deleteCartItem(cartItem: CartItem)

    @Query("DELETE FROM cart_items_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM cart_items_table")
    fun getShoppingCart(): LiveData<List<CartItem>>

    @Query("SELECT * FROM cart_items_table")
    suspend fun getCartData():List<CartItem>

    @Query("SELECT SUM(item_price * item_quantity) FROM cart_items_table")
    fun getCheckoutTotal(): LiveData<Double>

    @Query("SELECT SUM(item_price * item_quantity) FROM cart_items_table")
    suspend fun getCheckoutData(): Double

    @Query("SELECT item_price*item_quantity FROM cart_items_table WHERE item_id = :itemId")
    fun getItemTotal(itemId: Int): LiveData<Double>

    @Query("UPDATE cart_items_table SET item_quantity = item_quantity+1 WHERE item_id = :itemId")
    suspend fun increaseQuantity(itemId: Int)

    @Query("UPDATE cart_items_table SET item_quantity = item_quantity-1 WHERE item_id = :itemId")
    suspend fun decreaseQuantity(itemId: Int)

    @Query("SELECT item_quantity FROM cart_items_table WHERE item_id = :itemId")
    suspend fun getItemQuantity(itemId: Int): Int

}