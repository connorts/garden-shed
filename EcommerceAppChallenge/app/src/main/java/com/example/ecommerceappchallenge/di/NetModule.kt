package com.example.ecommerceappchallenge.di

import com.example.ecommerceappchallenge.BuildConfig
import com.example.ecommerceappchallenge.data.api.ProductService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://fakestoreapi.com/")
            .build()
    }

    @Singleton
    @Provides
    fun providesProductService(retrofit: Retrofit): ProductService {
        return retrofit.create(ProductService::class.java)
    }
}