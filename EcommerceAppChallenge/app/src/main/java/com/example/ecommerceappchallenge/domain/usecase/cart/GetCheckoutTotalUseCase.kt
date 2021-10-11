package com.example.ecommerceappchallenge.domain.usecase.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.ecommerceappchallenge.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import java.text.DecimalFormat

class GetCheckoutTotalUseCase(private val repository: CartRepository) {

    fun execute(): LiveData<Double> {
        return repository.getCheckoutTotal()
    }
}