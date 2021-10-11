package com.example.ecommerceappchallenge.di

import android.app.Application
import androidx.room.Room
import com.example.ecommerceappchallenge.data.local.CartItemDAO
import com.example.ecommerceappchallenge.data.local.GardenShedDatabase
import com.example.ecommerceappchallenge.data.local.OfferDAO
import com.example.ecommerceappchallenge.data.local.ProfileDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideGardenShedDatabase(app: Application): GardenShedDatabase {
        return Room.databaseBuilder(app,GardenShedDatabase::class.java, "garden_shed_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideCartItemDAO(database: GardenShedDatabase): CartItemDAO {
        return database.cartItemDAO
    }

    @Provides
    @Singleton
    fun provideProfileDAO(database: GardenShedDatabase): ProfileDAO {
        return database.profileDAO
    }

    @Singleton
    @Provides
    fun provideOfferDao(database: GardenShedDatabase): OfferDAO {
        return database.offerDAO
    }
}