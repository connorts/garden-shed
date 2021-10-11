package com.example.ecommerceappchallenge.viewmodel.cart

import androidx.lifecycle.*
import com.example.ecommerceappchallenge.view.util.SingleLiveEvent
import com.example.ecommerceappchallenge.data.model.CartItem
import com.example.ecommerceappchallenge.domain.usecase.cart.*
import com.example.ecommerceappchallenge.domain.usecase.profile.GetProfileDataUseCase
import com.example.ecommerceappchallenge.view.util.Event
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CartItemViewModel(
    getShoppingCartUseCase: GetShoppingCartUseCase,
    getCheckoutTotalUseCase: GetCheckoutTotalUseCase,
    private val getProfileDataUseCase: GetProfileDataUseCase,
    private val deleteCartItemUseCase: DeleteCartItemUseCase,
    private val decreaseItemQuantityUseCase: DecreaseItemQuantityUseCase,
    private val increaseItemQuantityUseCase: IncreaseItemQuantityUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    val shoppingCart = getShoppingCartUseCase.execute()
    val checkoutTotal = getCheckoutTotalUseCase.execute()

    private var _navigateToCheckout = SingleLiveEvent<Any>()
    val navigateToCheckout: LiveData<Any>
        get() = _navigateToCheckout

    private val statusMessage = MutableLiveData<Event<String>>()
    val message : LiveData<Event<String>>
        get() = statusMessage

    fun removeOrDecrease(cartItem: CartItem) {
        if (cartItem.quantity > 1) {
            decreaseQuantity(cartItem.id)
        } else {
            delete(cartItem)
        }
    }

    fun delete(cartItem: CartItem) {
        viewModelScope.launch(dispatcher) {
            deleteCartItemUseCase.execute(cartItem)
        }
    }

    private fun decreaseQuantity(itemId: Int) {
        viewModelScope.launch(dispatcher) {
            decreaseItemQuantityUseCase.execute(itemId)
        }
    }

    fun increaseQuantity(itemId: Int) {
        viewModelScope.launch(dispatcher) {
            increaseItemQuantityUseCase.execute(itemId)
        }
    }

    fun checkout() = viewModelScope.launch(dispatcher) {
        if (getProfileDataUseCase.execute()!=null) {
            _navigateToCheckout.callBackground()
        } else {
            statusMessage.postValue(Event("You need to create a profile first"))
        }
    }

}