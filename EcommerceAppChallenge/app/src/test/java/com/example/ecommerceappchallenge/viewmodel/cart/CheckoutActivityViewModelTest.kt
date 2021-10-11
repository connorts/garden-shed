package com.example.ecommerceappchallenge.viewmodel.cart

import android.net.Uri
import com.example.ecommerceappchallenge.InstantExecutorExtension
import com.example.ecommerceappchallenge.data.model.CartItem
import com.example.ecommerceappchallenge.data.model.Profile
import com.example.ecommerceappchallenge.data.model.Rating
import com.example.ecommerceappchallenge.domain.repository.FakeCartRepository
import com.example.ecommerceappchallenge.domain.repository.FakeProfileRepository
import com.example.ecommerceappchallenge.domain.usecase.cart.DeleteCartUseCase
import com.example.ecommerceappchallenge.domain.usecase.cart.GetCheckoutTotalUseCase
import com.example.ecommerceappchallenge.domain.usecase.profile.GetProfileUseCase
import com.example.ecommerceappchallenge.domain.usecase.cart.GetShoppingCartUseCase
import com.example.ecommerceappchallenge.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
class CheckoutActivityViewModelTest {


    private lateinit var viewModel: CheckoutActivityViewModel
    private val fakeCart = listOf(
        CartItem("category1","description1",1,"image1",1.11, Rating(1.1,1),"title1",1),
        CartItem("category2","description2",2,"image2",2.22, Rating(2.2,2),"title2",2))

    @BeforeEach
    fun setUp() {
        val fakeCartRepository = FakeCartRepository()
        val fakeProfileRepository = FakeProfileRepository()
        val getShoppingCartUseCase = GetShoppingCartUseCase(fakeCartRepository)
        val getCheckoutTotalUseCase = GetCheckoutTotalUseCase(fakeCartRepository)
        val getProfileUseCase = GetProfileUseCase(fakeProfileRepository)
        val deleteCartUseCase = DeleteCartUseCase(fakeCartRepository)
        viewModel = CheckoutActivityViewModel(getShoppingCartUseCase,getCheckoutTotalUseCase,getProfileUseCase,deleteCartUseCase)
    }

    @Test
    fun getShoppingCartTest() {
        val cart = viewModel.shoppingCart.getOrAwaitValue()
        assertThat(cart).isEqualTo(fakeCart)
    }

    @Test
    fun getCheckoutTotalTest() {
        val total = viewModel.checkoutTotal.getOrAwaitValue()
        assertThat(total).isEqualTo(3.33)
    }

    @Test
    fun getProfileTest() {
        val profile = viewModel.profile.getOrAwaitValue()
        assertThat(profile).isEqualTo(Profile(1,"fname1","lname1","email1","phone1","address1","password1", Uri.EMPTY))
    }

    @Test
    fun placeOrderTest() {
        runBlocking {
            viewModel.placeOrder()
            val cart = viewModel.shoppingCart.getOrAwaitValue()
            assertThat(cart).isEmpty()
        }
    }
}