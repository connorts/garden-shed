package com.example.ecommerceappchallenge.data.local

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ecommerceappchallenge.data.model.Profile

@Dao
interface ProfileDAO {

    @Insert
    suspend fun insertProfile(profile: Profile)

    @Delete
    suspend fun deleteProfile(profile: Profile)

    @Query("DELETE FROM user_profile_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM user_profile_table LIMIT 1")
    fun getProfile(): LiveData<Profile>

    @Query("SELECT * FROM user_profile_table LIMIT 1")
    fun getProfileData(): Profile

    @Query("UPDATE user_profile_table SET user_email = :email")
    fun updateEmail(email: String)

    @Query("UPDATE user_profile_table SET user_phone = :phone")
    fun updatePhone(phone: String)

    @Query("UPDATE user_profile_table SET user_address = :address")
    fun updateAddress(address: String)

    @Query("UPDATE user_profile_table SET user_photo = :photo")
    fun updatePhoto(photo: Uri)
}