package com.example.ecommerceappchallenge.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.ecommerceappchallenge.data.model.*

@Database(entities = [Profile::class, CartItem::class, ProductsItem::class],version = 1,exportSchema = false)
@TypeConverters(RatingConverter::class,PhotoConverter::class)
abstract class GardenShedDatabase: RoomDatabase() {

    abstract val profileDAO: ProfileDAO
    abstract val cartItemDAO: CartItemDAO
    abstract val offerDAO: OfferDAO

    companion object {

        @Volatile
        private var INSTANCE: GardenShedDatabase? = null
        fun getInstance(context: Context): GardenShedDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance==null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        GardenShedDatabase::class.java,
                        "user_profile_database"
                    ).build()
                }
                return instance
            }
        }

    }
}