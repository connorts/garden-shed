package com.example.ecommerceappchallenge.di

import com.example.ecommerceappchallenge.view.adapter.RecyclerAdapterCart
import com.example.ecommerceappchallenge.view.adapter.RecyclerAdapterCheckout
import com.example.ecommerceappchallenge.view.adapter.RecyclerAdapterProducts
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Singleton
    @Provides
    fun getRecyclerAdapterProduct(): RecyclerAdapterProducts {
        return RecyclerAdapterProducts()
    }

    @Singleton
    @Provides
    fun getRecyclerAdapterCart(): RecyclerAdapterCart {
        return RecyclerAdapterCart()
    }

    @Singleton
    @Provides
    fun getRecyclerAdapterCheckout(): RecyclerAdapterCheckout {
        return RecyclerAdapterCheckout()
    }
}