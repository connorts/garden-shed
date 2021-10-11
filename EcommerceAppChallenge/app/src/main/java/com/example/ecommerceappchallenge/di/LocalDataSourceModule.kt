package com.example.ecommerceappchallenge.di

import com.example.ecommerceappchallenge.data.local.CartItemDAO
import com.example.ecommerceappchallenge.data.local.OfferDAO
import com.example.ecommerceappchallenge.data.local.ProfileDAO
import com.example.ecommerceappchallenge.data.repository.dataSource.CartLocalDataSource
import com.example.ecommerceappchallenge.data.repository.dataSource.OffersLocalDataSource
import com.example.ecommerceappchallenge.data.repository.dataSource.ProfileLocalDataSource
import com.example.ecommerceappchallenge.data.repository.dataSourceImpl.CartLocalDataSourceImpl
import com.example.ecommerceappchallenge.data.repository.dataSourceImpl.OffersLocalDataSourceImpl
import com.example.ecommerceappchallenge.data.repository.dataSourceImpl.ProfileLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataSourceModule {

    @Provides
    @Singleton
    fun provideCartLocalDataSource(dao: CartItemDAO): CartLocalDataSource {
        return CartLocalDataSourceImpl(dao)
    }

    @Provides
    @Singleton
    fun provideProfileLocalDataSource(dao: ProfileDAO): ProfileLocalDataSource {
        return ProfileLocalDataSourceImpl(dao)
    }

    @Provides
    @Singleton
    fun provideOffersLocalDataSource(dao: OfferDAO): OffersLocalDataSource {
        return OffersLocalDataSourceImpl(dao)
    }
}