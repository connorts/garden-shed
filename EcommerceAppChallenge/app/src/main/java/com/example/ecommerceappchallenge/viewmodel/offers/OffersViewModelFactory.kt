package com.example.ecommerceappchallenge.viewmodel.offers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ecommerceappchallenge.domain.usecase.cart.InsertCartItemUseCase
import com.example.ecommerceappchallenge.domain.usecase.offers.GetSavedOfferUseCase
import kotlinx.coroutines.CoroutineDispatcher
import java.lang.IllegalArgumentException

class OffersViewModelFactory(private val insertCartItemUseCase: InsertCartItemUseCase,
                             private val getSavedOfferUseCase: GetSavedOfferUseCase,
                             private val dispatcher: CoroutineDispatcher
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OffersViewModel::class.java)) {
            return OffersViewModel(insertCartItemUseCase,getSavedOfferUseCase,dispatcher) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}