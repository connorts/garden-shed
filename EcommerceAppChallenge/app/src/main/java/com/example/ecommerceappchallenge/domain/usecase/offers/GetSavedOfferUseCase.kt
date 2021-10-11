package com.example.ecommerceappchallenge.domain.usecase.offers

import androidx.lifecycle.LiveData
import com.example.ecommerceappchallenge.data.model.ProductsItem
import com.example.ecommerceappchallenge.domain.repository.OffersRepository

class GetSavedOfferUseCase(private val repository: OffersRepository) {
    fun execute(): LiveData<ProductsItem> {
        return repository.getOffer()
    }
}