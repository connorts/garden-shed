package com.example.ecommerceappchallenge.data.model

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile_table")
data class Profile(

    @PrimaryKey(autoGenerate = true)
    val uid: Int,

    @ColumnInfo(name = "user_first_name")
    val firstName: String,

    @ColumnInfo(name = "user_last_name")
    val lastName: String,

    @ColumnInfo(name = "user_email")
    var email: String,

    @ColumnInfo(name = "user_phone")
    var phone: String,

    @ColumnInfo(name = "user_address")
    var address: String,

    @ColumnInfo(name = "user_password")
    val password: String,

    @ColumnInfo(name = "user_photo")
    var photo: Uri?
)