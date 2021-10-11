package com.example.ecommerceappchallenge.data.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductServiceTest {
    private lateinit var service: ProductService
    private lateinit var server: MockWebServer

    @BeforeEach
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductService::class.java)
    }

    @AfterEach
    fun tearDown() {
        server.shutdown()
    }

    private fun enqueueMockResponse(fileName: String) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }

    @Test
    fun getProducts_sentRequest_receivedExpected() {
        runBlocking {
            enqueueMockResponse("apiresponse.json")
            val responseBody = service.getProducts().body()
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/products")
        }
    }

    @Test
    fun getProducts_receivedResponse_correctListSize() {
        runBlocking {
            enqueueMockResponse("apiresponse.json")
            val responseBody = service.getProducts().body()
            assertThat(responseBody!!.size).isEqualTo(20)
        }
    }

    @Test
    fun getProducts_receivedResponse_correctContent() {
        runBlocking {
            enqueueMockResponse("apiresponse.json")
            val responseBody = service.getProducts().body()
            val product = responseBody!![0]
            assertThat(product.category).isEqualTo("men's clothing")
            assertThat(product.title).isEqualTo("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops")
            assertThat(product.image).isEqualTo("https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg")
        }
    }

    @Test
    fun getProductsByCategory_sentRequest_receivedExpected() {
        runBlocking {
            enqueueMockResponse("apiresponse.json")
            val responseBody = service.getProductsByCategory("jewelery").body()
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/products/category/jewelery")
        }
    }

    @Test
    fun getProductsByCategory_receivedResponse_correctListSize() {
        runBlocking {
            enqueueMockResponse("apiresponse.json")
            val responseBody = service.getProductsByCategory("jewelery").body()
            assertThat(responseBody!!.size).isEqualTo(5)
        }
    }

    @Test
    fun getProductsByCategory_receivedResponse_correctContent() {
        runBlocking {
            enqueueMockResponse("apiresponse.json")
            val responseBody = service.getProductsByCategory("jewelery").body()
            val product = responseBody!![0]
            assertThat(product.category).isEqualTo("jewelery")
            assertThat(product.title).isEqualTo("John Hardy Women's Legends Naga Gold & Silver Dragon Station Chain Bracelet")
            assertThat(product.image).isEqualTo("https://fakestoreapi.com/img/71pWzhdJNwL._AC_UL640_QL65_ML3_.jpg")
        }
    }
}