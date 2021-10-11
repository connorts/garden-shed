package com.example.ecommerceappchallenge.viewmodel.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.ecommerceappchallenge.view.util.SingleLiveEvent

class PlaceOrderActivityViewModel: ViewModel() {

    private val _navigateToMain = SingleLiveEvent<Any>()
    val navigateToMain: LiveData<Any>
        get() = _navigateToMain

    fun keepShopping() {
        _navigateToMain.call()
    }
}