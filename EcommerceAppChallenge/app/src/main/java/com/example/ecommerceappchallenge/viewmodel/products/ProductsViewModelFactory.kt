package com.example.ecommerceappchallenge.viewmodel.products

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ecommerceappchallenge.domain.usecase.cart.GetCartDataUseCase
import com.example.ecommerceappchallenge.domain.usecase.cart.IncreaseItemQuantityUseCase
import com.example.ecommerceappchallenge.domain.usecase.cart.InsertCartItemUseCase
import com.example.ecommerceappchallenge.domain.usecase.products.GetProductsByCategoryUseCase
import com.example.ecommerceappchallenge.domain.usecase.products.GetProductsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import java.lang.IllegalArgumentException

class ProductsViewModelFactory(private val app: Application,
                               private val getProductsUseCase: GetProductsUseCase,
                               private val increaseItemQuantityUseCase: IncreaseItemQuantityUseCase,
                               private val insertCartItemUseCase: InsertCartItemUseCase,
                               private val getCartDataUseCase: GetCartDataUseCase,
                               private val getProductsByCategoryUseCase: GetProductsByCategoryUseCase,
                               private val dispatcher: CoroutineDispatcher
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductsViewModel::class.java)) {
            return ProductsViewModel(app,
                getProductsUseCase,
                increaseItemQuantityUseCase,
                insertCartItemUseCase,
                getCartDataUseCase,
                getProductsByCategoryUseCase,
                dispatcher) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}