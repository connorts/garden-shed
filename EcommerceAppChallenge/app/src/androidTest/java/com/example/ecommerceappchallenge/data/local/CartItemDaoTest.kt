package com.example.ecommerceappchallenge.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.ecommerceappchallenge.data.model.CartItem
import com.example.ecommerceappchallenge.data.model.Rating
import com.example.ecommerceappchallenge.getOrAwaitValue
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class CartItemDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var dao: CartItemDAO
    private lateinit var database: GardenShedDatabase

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            GardenShedDatabase::class.java
        ).build()
        dao = database.cartItemDAO
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertCartItemTest() {
        runBlocking {
            val cartItem = CartItem("category1","description1",1,"image1",1.1, Rating(1.0,1),"title1",1)
            dao.insertCartItem(cartItem)

            val allItems = dao.getCartData()
            Truth.assertThat(allItems).isEqualTo(listOf(cartItem))
        }
    }

    @Test
    fun deleteCartItemTest() {
        runBlocking {
            val cartItem = CartItem("category1","description1",1,"image1",1.1, Rating(1.0,1),"title1",1)
            dao.insertCartItem(cartItem)
            dao.deleteCartItem(cartItem)

            val allItems = dao.getCartData()
            Truth.assertThat(allItems).isEmpty()
        }
    }

    @Test
    fun deleteAllTest() {
        runBlocking {
            val cartItem1 = CartItem("category1","description1",1,"image1",1.1, Rating(1.0,1),"title1",1)
            val cartItem2 = CartItem("category2","description2",2,"image2",1.2, Rating(2.0,2),"title2",2)
            dao.insertCartItem(cartItem1)
            dao.insertCartItem(cartItem2)
            dao.deleteAll()

            val allItems = dao.getCartData()
            Truth.assertThat(allItems).isEmpty()
        }
    }

    @Test
    fun getShoppingCartTest() {
        runBlocking {
            val cartItem1 = CartItem("category1","description1",1,"image1",1.1, Rating(1.0,1),"title1",1)
            val cartItem2 = CartItem("category2","description2",2,"image2",1.2, Rating(2.0,2),"title2",2)
            dao.insertCartItem(cartItem1)
            dao.insertCartItem(cartItem2)
            val listAll = listOf(cartItem1,cartItem2)

            val allItems = dao.getShoppingCart().getOrAwaitValue()
            Truth.assertThat(allItems).isEqualTo(listAll)
        }
    }

    @Test
    fun getCartDataTest() {
        runBlocking {
            val cartItem1 = CartItem("category1","description1",1,"image1",1.1, Rating(1.0,1),"title1",1)
            val cartItem2 = CartItem("category2","description2",2,"image2",1.2, Rating(2.0,2),"title2",2)
            dao.insertCartItem(cartItem1)
            dao.insertCartItem(cartItem2)
            val listAll = listOf(cartItem1,cartItem2)

            val allItems = dao.getCartData()
            Truth.assertThat(allItems).isEqualTo(listAll)
        }
    }

    @Test
    fun checkoutTotalTest() {
        runBlocking {
            val cartItem1 = CartItem("category1","description1",1,"image1",1.11, Rating(1.0,1),"title1",1)
            val cartItem2 = CartItem("category2","description2",2,"image2",1.22, Rating(2.0,2),"title2",2)
            dao.insertCartItem(cartItem1)
            dao.insertCartItem(cartItem2)

            val cartTotal = dao.getCheckoutTotal().getOrAwaitValue()
            Truth.assertThat(cartTotal).isEqualTo(3.55)
        }
    }

    @Test
    fun getItemTotalTest() {
        runBlocking {
            val cartItem = CartItem("category1","description1",1,"image1",1.11, Rating(1.0,1),"title1",4)
            dao.insertCartItem(cartItem)

            val itemTotal = dao.getItemTotal(cartItem.id).getOrAwaitValue()
            Truth.assertThat(itemTotal).isEqualTo(4.44)
        }
    }

    @Test
    fun increaseQuantityTest() {
        runBlocking {
            val cartItem = CartItem("category1","description1",1,"image1",1.1, Rating(1.0,1),"title1",1)
            dao.insertCartItem(cartItem)
            dao.increaseQuantity(cartItem.id)

            val allItems = dao.getCartData()
            Truth.assertThat(allItems[0].quantity).isEqualTo(cartItem.quantity+1)
        }
    }

    @Test
    fun decreaseQuantityTest() {
        runBlocking {
            val cartItem = CartItem("category1","description1",1,"image1",1.1, Rating(1.0,1),"title1",2)
            dao.insertCartItem(cartItem)
            dao.decreaseQuantity(cartItem.id)

            val allItems = dao.getCartData()
            Truth.assertThat(allItems[0].quantity).isEqualTo(cartItem.quantity-1)
        }
    }

}
