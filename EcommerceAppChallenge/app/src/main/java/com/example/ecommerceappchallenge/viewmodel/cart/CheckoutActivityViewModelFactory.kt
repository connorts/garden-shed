package com.example.ecommerceappchallenge.viewmodel.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ecommerceappchallenge.domain.usecase.cart.DeleteCartUseCase
import com.example.ecommerceappchallenge.domain.usecase.cart.GetCheckoutTotalUseCase
import com.example.ecommerceappchallenge.domain.usecase.profile.GetProfileUseCase
import com.example.ecommerceappchallenge.domain.usecase.cart.GetShoppingCartUseCase
import java.lang.IllegalArgumentException

class CheckoutActivityViewModelFactory(
    private val getShoppingCartUseCase: GetShoppingCartUseCase,
    private val getCheckoutTotalUseCase: GetCheckoutTotalUseCase,
    private val getProfileUseCase: GetProfileUseCase,
    private val deleteCartUseCase: DeleteCartUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CheckoutActivityViewModel::class.java)) {
            return CheckoutActivityViewModel(getShoppingCartUseCase,getCheckoutTotalUseCase,getProfileUseCase,deleteCartUseCase) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}