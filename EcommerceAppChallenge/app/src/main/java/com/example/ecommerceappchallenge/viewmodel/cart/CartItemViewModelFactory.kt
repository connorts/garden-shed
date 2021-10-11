package com.example.ecommerceappchallenge.viewmodel.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ecommerceappchallenge.domain.usecase.cart.*
import com.example.ecommerceappchallenge.domain.usecase.profile.GetProfileDataUseCase
import kotlinx.coroutines.CoroutineDispatcher
import java.lang.IllegalArgumentException

class CartItemViewModelFactory(private val getShoppingCartUseCase: GetShoppingCartUseCase,
                               private val getCheckoutTotalUseCase: GetCheckoutTotalUseCase,
                               private val getProfileDataUseCase: GetProfileDataUseCase,
                               private val deleteCartItemUseCase: DeleteCartItemUseCase,
                               private val decreaseItemQuantityUseCase: DecreaseItemQuantityUseCase,
                               private val increaseItemQuantityUseCase: IncreaseItemQuantityUseCase,
                               private val dispatcher: CoroutineDispatcher
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CartItemViewModel::class.java)) {
            return CartItemViewModel(
                getShoppingCartUseCase,
                getCheckoutTotalUseCase,
                getProfileDataUseCase,
                deleteCartItemUseCase,
                decreaseItemQuantityUseCase,
                increaseItemQuantityUseCase,
                dispatcher) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}