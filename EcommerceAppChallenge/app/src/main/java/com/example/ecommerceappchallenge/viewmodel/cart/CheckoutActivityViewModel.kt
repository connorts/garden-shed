package com.example.ecommerceappchallenge.viewmodel.cart

import androidx.lifecycle.*
import com.example.ecommerceappchallenge.view.util.SingleLiveEvent
import com.example.ecommerceappchallenge.domain.usecase.cart.DeleteCartUseCase
import com.example.ecommerceappchallenge.domain.usecase.cart.GetCheckoutTotalUseCase
import com.example.ecommerceappchallenge.domain.usecase.profile.GetProfileUseCase
import com.example.ecommerceappchallenge.domain.usecase.cart.GetShoppingCartUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CheckoutActivityViewModel(
    private val getShoppingCartUseCase: GetShoppingCartUseCase,
    private val getCheckoutTotalUseCase: GetCheckoutTotalUseCase,
    private val getProfileUseCase: GetProfileUseCase,
    private val deleteCartUseCase: DeleteCartUseCase
): ViewModel() {

    val shoppingCart = getShoppingCartUseCase.execute()
    val profile = getProfileUseCase.execute()
    val checkoutTotal = getCheckoutTotalUseCase.execute()

    val _navigateToPlaceOrder = SingleLiveEvent<Any>()
    val navigateToPlaceOrder: LiveData<Any>
        get() = _navigateToPlaceOrder

    fun placeOrder() {
        _navigateToPlaceOrder.call()
        deleteCart()
    }

    private fun deleteCart() = viewModelScope.launch(Dispatchers.IO) { deleteCartUseCase.execute() }
}