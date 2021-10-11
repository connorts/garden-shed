package com.example.ecommerceappchallenge.di

import com.example.ecommerceappchallenge.data.api.ProductService
import com.example.ecommerceappchallenge.data.local.CartItemDAO
import com.example.ecommerceappchallenge.data.local.ProfileDAO
import com.example.ecommerceappchallenge.data.repository.dataSource.CartLocalDataSource
import com.example.ecommerceappchallenge.data.repository.dataSource.ProductsRemoteDataSource
import com.example.ecommerceappchallenge.data.repository.dataSource.ProfileLocalDataSource
import com.example.ecommerceappchallenge.data.repository.dataSourceImpl.CartLocalDataSourceImpl
import com.example.ecommerceappchallenge.data.repository.dataSourceImpl.ProductsRemoteDataSourceImpl
import com.example.ecommerceappchallenge.data.repository.dataSourceImpl.ProfileLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSourceModule {

    @Provides
    @Singleton
    fun provideProductsRemoteDataSource(apiService: ProductService): ProductsRemoteDataSource {
        return ProductsRemoteDataSourceImpl(apiService)
    }
}