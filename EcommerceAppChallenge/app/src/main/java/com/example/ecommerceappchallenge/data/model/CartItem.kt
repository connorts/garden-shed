package com.example.ecommerceappchallenge.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items_table")
data class CartItem(

    @ColumnInfo(name = "item_category")
    val category: String,

    @ColumnInfo(name = "item_description")
    val description: String,

    @PrimaryKey
    @ColumnInfo(name = "item_id")
    val id: Int,

    @ColumnInfo(name = "item_image")
    val image: String,

    @ColumnInfo(name = "item_price")
    val price: Double,

    @ColumnInfo(name = "item_rating")
    val rating: Rating,

    @ColumnInfo(name = "item_title")
    val title: String,

    @ColumnInfo(name = "item_quantity")
    var quantity: Int

)