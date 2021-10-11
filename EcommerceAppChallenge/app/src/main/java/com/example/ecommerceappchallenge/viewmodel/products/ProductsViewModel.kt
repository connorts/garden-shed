package com.example.ecommerceappchallenge.viewmodel.products

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.example.ecommerceappchallenge.data.model.CartItem
import com.example.ecommerceappchallenge.data.model.Products
import com.example.ecommerceappchallenge.data.model.ProductsItem
import com.example.ecommerceappchallenge.data.util.Resource
import com.example.ecommerceappchallenge.domain.usecase.cart.GetCartDataUseCase
import com.example.ecommerceappchallenge.domain.usecase.cart.IncreaseItemQuantityUseCase
import com.example.ecommerceappchallenge.domain.usecase.cart.InsertCartItemUseCase
import com.example.ecommerceappchallenge.domain.usecase.products.GetProductsByCategoryUseCase
import com.example.ecommerceappchallenge.domain.usecase.products.GetProductsUseCase
import com.example.ecommerceappchallenge.view.util.CartItemConverter
import com.example.ecommerceappchallenge.view.util.Event
import kotlinx.coroutines.*

class ProductsViewModel(
    private val app: Application,
    private val getProductsUseCase: GetProductsUseCase,
    private val increaseItemQuantityUseCase: IncreaseItemQuantityUseCase,
    private val insertCartItemUseCase: InsertCartItemUseCase,
    private val getCartDataUseCase: GetCartDataUseCase,
    private val getProductsByCategoryUseCase: GetProductsByCategoryUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): AndroidViewModel(app) {
    val products: MutableLiveData<Resource<Products>> = MutableLiveData()

    private val statusMessage = MutableLiveData<Event<String>>()
    val message : LiveData<Event<String>>
        get() = statusMessage

    fun getProducts() = viewModelScope.launch(dispatcher) {
        products.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)){
                val apiResource = getProductsUseCase.execute()
                products.postValue(apiResource)
            } else {
                products.postValue(Resource.Error("Internet is unavailable"))
            }
        } catch (e: Exception) {
            products.postValue(Resource.Error(e.message.toString()))
        }
    }

    fun filterByAll() = viewModelScope.launch(dispatcher) {
        products.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)){
                val apiResource = getProductsUseCase.execute()
                products.postValue(apiResource)
            } else {
                products.postValue(Resource.Error("Internet is unavailable"))
            }
        } catch (e: Exception) {
            products.postValue(Resource.Error(e.message.toString()))
        }
    }

    fun filterByMensClothing() = viewModelScope.launch(dispatcher) {
        try {
            products.postValue(Resource.Loading())
            if (isNetworkAvailable(app)) {
                val apiResource = getProductsByCategoryUseCase.execute("men's clothing")
                products.postValue(apiResource)
            } else {
                products.postValue(Resource.Error("Internet is unavailable"))
            }
        } catch (e:Exception) {
            products.postValue(Resource.Error(e.message.toString()))
        }
    }

    fun filterByWomensClothing() = viewModelScope.launch(dispatcher) {
        try {
            products.postValue(Resource.Loading())
            if (isNetworkAvailable(app)) {
                val apiResource = getProductsByCategoryUseCase.execute("women's clothing")
                products.postValue(apiResource)
            } else {
                products.postValue(Resource.Error("Internet is unavailable"))
            }
        } catch (e:Exception) {
            products.postValue(Resource.Error(e.message.toString()))
        }
    }

    fun filterByJewelery() = viewModelScope.launch(dispatcher) {
        try {
            products.postValue(Resource.Loading())
            if (isNetworkAvailable(app)) {
                val apiResource = getProductsByCategoryUseCase.execute("jewelery")
                products.postValue(apiResource)
            } else {
                products.postValue(Resource.Error("Internet is unavailable"))
            }
        } catch (e:Exception) {
            products.postValue(Resource.Error(e.message.toString()))
        }
    }

    fun filterByElectronics() = viewModelScope.launch(dispatcher) {
        try {
            products.postValue(Resource.Loading())
            if (isNetworkAvailable(app)) {
                val apiResource = getProductsByCategoryUseCase.execute("electronics")
                products.postValue(apiResource)
            } else {
                products.postValue(Resource.Error("Internet is unavailable"))
            }
        } catch (e:Exception) {
            products.postValue(Resource.Error(e.message.toString()))
        }
    }

    private fun isNetworkAvailable(context: Context?): Boolean {
        return true
//        if (context == null) return false
//        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
//            if (capabilities != null) {
//                when {
//                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
//                        return true
//                    }
//                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
//                        return true
//                    }
//                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
//                        return true
//                    }
//                }
//            }
//        } else {
//            val activeNetworkInfo = connectivityManager.activeNetworkInfo
//            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
//                return true
//            }
//        }
//        return false
    }

    fun insertOrIncrease(productsItem: ProductsItem) {
        viewModelScope.launch(dispatcher) {
            // if item exists in cart-> increase quantity. Else -> add to cart
            if (getCartDataUseCase.execute().any { it.id == productsItem.id }) {
                increaseQuantity(productsItem.id)
            } else { insert(CartItemConverter.productToCartItem(productsItem))}
        }
        statusMessage.value = Event("Added to cart!")
    }

    private fun insert(cartItem: CartItem) = viewModelScope.launch(dispatcher) {
        insertCartItemUseCase.execute(cartItem)
    }

    private fun increaseQuantity(itemId: Int) = viewModelScope.launch(dispatcher) {
        increaseItemQuantityUseCase.execute(itemId)
    }

}