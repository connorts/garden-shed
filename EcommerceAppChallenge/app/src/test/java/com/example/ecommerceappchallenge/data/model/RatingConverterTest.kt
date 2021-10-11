package com.example.ecommerceappchallenge.data.model

import com.google.common.truth.Truth

import org.junit.jupiter.api.Test

class RatingConverterTest {

    private var ratingConverter = RatingConverter()

    @Test
    fun fromStringToRating_InputString_ReturnsExpectedRating() {
        val stringInput = "3.3/444"
        val expectedRating = Rating(3.3,444)
        val result = ratingConverter.fromStringToRating(stringInput)
        Truth.assertThat(result).isEqualTo(expectedRating)
    }

    @Test
    fun ratingToString_InputRating_ReturnsExpectedString() {
        val ratingInput = Rating(3.3,444)
        val expectedString = "3.3/444"
        val result = ratingConverter.ratingToString(ratingInput)
        Truth.assertThat(result).isEqualTo(expectedString)
    }
}