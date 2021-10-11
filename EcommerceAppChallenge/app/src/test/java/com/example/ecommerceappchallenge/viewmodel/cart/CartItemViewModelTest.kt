package com.example.ecommerceappchallenge.viewmodel.cart

import com.example.ecommerceappchallenge.CoroutinesTestExtension
import com.example.ecommerceappchallenge.InstantExecutorExtension
import com.example.ecommerceappchallenge.data.model.CartItem
import com.example.ecommerceappchallenge.data.model.Rating
import com.example.ecommerceappchallenge.domain.repository.FakeCartRepository
import com.example.ecommerceappchallenge.domain.repository.FakeProfileRepository
import com.example.ecommerceappchallenge.domain.usecase.cart.*
import com.example.ecommerceappchallenge.domain.usecase.profile.GetProfileDataUseCase
import com.example.ecommerceappchallenge.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith( value = [InstantExecutorExtension::class, CoroutinesTestExtension::class])
class CartItemViewModelTest {

    lateinit var viewModel: CartItemViewModel
    private val dispatcher = TestCoroutineDispatcher()
    private val fakeCart = listOf(
        CartItem("category1","description1",0,"image1",1.11, Rating(1.1,1),"title1",1),
        CartItem("category2","description2",1,"image2",2.22, Rating(2.2,2),"title2",2)
    )

    @BeforeEach
    fun setUp() {
        val fakeCartRepository = FakeCartRepository()
        val fakeProfileRepository = FakeProfileRepository()
        val getShoppingCartUseCase = GetShoppingCartUseCase(fakeCartRepository)
        val getCheckoutTotalUseCase = GetCheckoutTotalUseCase(fakeCartRepository)
        val getProfileDataUseCase = GetProfileDataUseCase(fakeProfileRepository)
        val deleteCartItemUseCase = DeleteCartItemUseCase(fakeCartRepository)
        val decreaseItemQuantityUseCase = DecreaseItemQuantityUseCase(fakeCartRepository)
        val increaseItemQuantityUseCase = IncreaseItemQuantityUseCase(fakeCartRepository)
        viewModel = CartItemViewModel(getShoppingCartUseCase,getCheckoutTotalUseCase,getProfileDataUseCase,deleteCartItemUseCase,decreaseItemQuantityUseCase,increaseItemQuantityUseCase,dispatcher)
    }

    @Test
    fun getShoppingCartTest() {
        runBlocking {
            val cart = viewModel.shoppingCart.getOrAwaitValue()
            assertThat(cart).isEqualTo(fakeCart)
        }
    }

    @Test
    fun getCheckoutTotalTest() {
        runBlocking {
            val total = viewModel.checkoutTotal.getOrAwaitValue()
            assertThat(total).isEqualTo(3.33)
        }
    }

    @Test
    fun increaseQuantityTest() = runBlocking {
        viewModel.increaseQuantity(fakeCart[0].id)
        val cart = viewModel.shoppingCart.getOrAwaitValue()
        assertThat(cart[0].quantity).isEqualTo(fakeCart[0].quantity+1)
    }

    @Test
    fun deleteTest() = runBlocking {
        viewModel.delete(fakeCart[1])
        val cart = viewModel.shoppingCart.getOrAwaitValue()
        assertThat(cart).isEqualTo(listOf(fakeCart[0]))
    }

    @Test
    fun removeOrDecrease_quantity1_itemRemoved() {
        runBlocking {
            viewModel.removeOrDecrease(
                CartItem(
                    "category1",
                    "description1",
                    0,
                    "image1",
                    1.11,
                    Rating(1.1, 1),
                    "title1",
                    1
                )
            )

            val cart = viewModel.shoppingCart.getOrAwaitValue()
            assertThat(cart).isEqualTo(
                listOf(
                    CartItem(
                        "category2",
                        "description2",
                        1,
                        "image2",
                        2.22,
                        Rating(2.2, 2),
                        "title2",
                        2
                    )
                )
            )
        }

    }

    @Test
    fun removeOrDecrease_quantityGreaterThan1_itemQuantityReduced() {
        runBlocking {
            viewModel.removeOrDecrease(
                CartItem(
                    "category2",
                    "description2",
                    1,
                    "image2",
                    2.22,
                    Rating(2.2, 2),
                    "title2",
                    2
                )
            )

            val cart = viewModel.shoppingCart.getOrAwaitValue()
            assertThat(cart).isEqualTo(
                listOf(
                    CartItem(
                        "category1",
                        "description1",
                        0,
                        "image1",
                        1.11,
                        Rating(1.1, 1),
                        "title1",
                        1
                    ),
                    CartItem(
                        "category2",
                        "description2",
                        1,
                        "image2",
                        2.22,
                        Rating(2.2, 2),
                        "title2",
                        1
                    )
                )
            )
        }
    }
}