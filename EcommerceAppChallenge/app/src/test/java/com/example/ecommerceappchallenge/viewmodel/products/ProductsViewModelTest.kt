package com.example.ecommerceappchallenge.viewmodel.products

import android.app.Application
import com.example.ecommerceappchallenge.CoroutinesTestExtension
import com.example.ecommerceappchallenge.InstantExecutorExtension
import com.example.ecommerceappchallenge.data.model.CartItem
import com.example.ecommerceappchallenge.data.model.ProductsItem
import com.example.ecommerceappchallenge.data.model.Rating
import com.example.ecommerceappchallenge.domain.repository.FakeCartRepository
import com.example.ecommerceappchallenge.domain.repository.FakeProductsRepository
import com.example.ecommerceappchallenge.domain.usecase.cart.GetCartDataUseCase
import com.example.ecommerceappchallenge.domain.usecase.cart.IncreaseItemQuantityUseCase
import com.example.ecommerceappchallenge.domain.usecase.cart.InsertCartItemUseCase
import com.example.ecommerceappchallenge.domain.usecase.products.GetProductsByCategoryUseCase
import com.example.ecommerceappchallenge.domain.usecase.products.GetProductsUseCase
import com.example.ecommerceappchallenge.getOrAwaitValue
import com.example.ecommerceappchallenge.view.util.CartItemConverter
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class,CoroutinesTestExtension::class)
internal class ProductsViewModelTest {

    private lateinit var viewModel: ProductsViewModel
    private lateinit var getCartDataUseCase: GetCartDataUseCase
    private val dispatcher = TestCoroutineDispatcher()
    private val product1 = ProductsItem("men's clothing","desc1",1,"img1",1.00, Rating(1.00,1),"title1")
    private val product2 = ProductsItem("women's clothing","desc2",2,"img2",2.00, Rating(2.00,2),"title2")
    private val product3 = ProductsItem("jewelery","desc3",3,"img3",3.00, Rating(3.00,3),"title3")
    private val product4 = ProductsItem("electronics","desc4",4,"img4",4.00, Rating(4.00,4),"title4")
    private val fakeProducts = listOf(product1,product2,product3,product4)

    @BeforeEach
    fun setUp() {
        val fakeCartRepository = FakeCartRepository()
        val fakeProductsRepository = FakeProductsRepository()
        val app = Application()
        val getProductsUseCase = GetProductsUseCase(fakeProductsRepository)
        val increaseItemQuantityUseCase = IncreaseItemQuantityUseCase(fakeCartRepository)
        val insertCartItemUseCase = InsertCartItemUseCase(fakeCartRepository)
        getCartDataUseCase = GetCartDataUseCase(fakeCartRepository)
        val getProductsByCategoryUseCase = GetProductsByCategoryUseCase(fakeProductsRepository)
        viewModel = ProductsViewModel(app,
            getProductsUseCase,
            increaseItemQuantityUseCase,
            insertCartItemUseCase,
            getCartDataUseCase,
            getProductsByCategoryUseCase,
            dispatcher)
    }

//    @Test
//    fun getMessage() {
//    }

    @Test
    fun getProductsTest() = runBlocking {
        viewModel.getProducts()
        val products = viewModel.products.getOrAwaitValue().data
        assertThat(products).isEqualTo(fakeProducts)
    }

    @Test
    fun filterByAllTest() = runBlocking {
        viewModel.getProducts()
        val products = viewModel.products.getOrAwaitValue().data
        assertThat(products).isEqualTo(fakeProducts)
    }

    @Test
    fun filterByMensClothingTest() = runBlocking(dispatcher) {
        viewModel.filterByMensClothing()
        val products = viewModel.products.getOrAwaitValue().data
        assertThat(products).isEqualTo(listOf(product1))
    }

    @Test
    fun filterByWomensClothingTest() = runBlocking(dispatcher) {
        viewModel.filterByWomensClothing()
        val products = viewModel.products.getOrAwaitValue().data
        assertThat(products).isEqualTo(listOf(product2))
    }

    @Test
    fun filterByJeweleryTest() = runBlocking(dispatcher) {
        viewModel.filterByJewelery()
        val products = viewModel.products.getOrAwaitValue().data
        assertThat(products).isEqualTo(listOf(product3))
    }

    @Test
    fun filterByElectronicsTest() = runBlocking(dispatcher) {
        viewModel.filterByElectronics()
        val products = viewModel.products.getOrAwaitValue().data
        assertThat(products).isEqualTo(listOf(product4))
    }

    @Test
    fun insertOrIncrease_quantity0_addItemToCart() = runBlocking {
        viewModel.insertOrIncrease(product4)
        val cart = getCartDataUseCase.execute()
        assertThat(cart).contains(CartItemConverter.productToCartItem(product4))
    }

    @Test
    fun insertOrIncrease_quantityGreaterThan1_increaseQuantity() = runBlocking {
        viewModel.insertOrIncrease(ProductsItem("category2","description2",1,"image2",2.22, Rating(2.2,2),"title2"))
        val cart = getCartDataUseCase.execute()
        assertThat(cart).contains(CartItem("category2","description2",1,"image2",2.22, Rating(2.2,2),"title2",3))
    }

}