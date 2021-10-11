package com.example.ecommerceappchallenge.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ecommerceappchallenge.data.model.ProductsItem

@Dao
interface OfferDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOffer(productsItem: ProductsItem)

    @Query("DELETE FROM offers_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM offers_table LIMIT 1")
    fun getOffer(): LiveData<ProductsItem>

}