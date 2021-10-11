package com.example.ecommerceappchallenge.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "offers_table")
data class ProductsItem(

    @SerializedName("category")
    val category: String,

    @SerializedName("description")
    val description: String,

    @PrimaryKey
    @SerializedName("id")
    val id: Int,

    @SerializedName("image")
    val image: String,

    @SerializedName("price")
    val price: Double,

    @SerializedName("rating")
    val rating: Rating,

    @SerializedName("title")
    val title: String

): Serializable