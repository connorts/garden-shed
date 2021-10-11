package com.example.ecommerceappchallenge.di

import com.example.ecommerceappchallenge.data.repository.impl.CartRepositoryImpl
import com.example.ecommerceappchallenge.data.repository.impl.ProductRepositoryImpl
import com.example.ecommerceappchallenge.data.repository.dataSource.CartLocalDataSource
import com.example.ecommerceappchallenge.data.repository.dataSource.OffersLocalDataSource
import com.example.ecommerceappchallenge.data.repository.dataSource.ProductsRemoteDataSource
import com.example.ecommerceappchallenge.data.repository.dataSource.ProfileLocalDataSource
import com.example.ecommerceappchallenge.data.repository.impl.OffersRepositoryImpl
import com.example.ecommerceappchallenge.domain.repository.CartRepository
import com.example.ecommerceappchallenge.domain.repository.ProductRepository
import com.example.ecommerceappchallenge.domain.repository.ProfileRepository
import com.example.ecommerceappchallenge.data.repository.impl.ProfileRepositoryImpl
import com.example.ecommerceappchallenge.domain.repository.OffersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesProductRepository(productsRemoteDataSource: ProductsRemoteDataSource): ProductRepository {
        return ProductRepositoryImpl(productsRemoteDataSource)
    }

    @Provides
    @Singleton
    fun providesCartRepository(cartLocalDataSource: CartLocalDataSource): CartRepository {
        return CartRepositoryImpl(cartLocalDataSource)
    }

    @Provides
    @Singleton
    fun providesProfileRepository(profileLocalDataSource: ProfileLocalDataSource): ProfileRepository {
        return ProfileRepositoryImpl(profileLocalDataSource)
    }

    @Provides
    @Singleton
    fun providesOffersRepository(offersLocalDataSource: OffersLocalDataSource): OffersRepository {
        return OffersRepositoryImpl(offersLocalDataSource)
    }
}