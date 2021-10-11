package com.example.ecommerceappchallenge.data.model

import androidx.room.TypeConverter

class RatingConverter {

    @TypeConverter
    fun fromStringToRating(value: String): Rating {
        val breakIndex = value.indexOf("/")
        var rate = ""
        var count = ""
        for (i in 0..breakIndex-1) {
            rate += value[i]
        }
        for (i in (breakIndex+1)..value.length-1) {
            count += value[i]
        }
        return Rating(rate.toDouble(),Integer.parseInt(count))
    }

    @TypeConverter
    fun ratingToString(rating: Rating): String {
        var result = ""
        result += rating.rate.toString()
        result += "/"
        result += rating.count.toString()
        return result
    }
}