package com.example.ecommerceappchallenge.data.model

import android.net.Uri
import androidx.room.TypeConverter

class PhotoConverter {

    @TypeConverter
    fun fromStringToUri(string: String): Uri {
        return Uri.parse(string)
    }

    @TypeConverter
    fun fromUriToString(uri: Uri): String {
        return uri.toString()
    }
}