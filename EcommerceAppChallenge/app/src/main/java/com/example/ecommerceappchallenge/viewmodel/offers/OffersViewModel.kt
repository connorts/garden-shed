package com.example.ecommerceappchallenge.viewmodel.offers

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceappchallenge.data.model.CartItem
import com.example.ecommerceappchallenge.data.model.ProductsItem
import com.example.ecommerceappchallenge.domain.usecase.cart.InsertCartItemUseCase
import com.example.ecommerceappchallenge.domain.usecase.offers.GetSavedOfferUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OffersViewModel(private val insertCartItemUseCase: InsertCartItemUseCase,
                      getSavedOfferUseCase: GetSavedOfferUseCase,
                      private val dispatcher: CoroutineDispatcher = Dispatchers.IO): ViewModel() {

    private var deal = getSavedOfferUseCase.execute()
    val offer: LiveData<ProductsItem>
        get() = deal

    fun addDealToCart(cartItem: CartItem) = viewModelScope.launch(dispatcher) {
        insertCartItemUseCase.execute(cartItem)
    }

}