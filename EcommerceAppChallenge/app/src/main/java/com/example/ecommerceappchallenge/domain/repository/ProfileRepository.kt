package com.example.ecommerceappchallenge.domain.repository

import android.net.Uri
import androidx.lifecycle.LiveData
import com.example.ecommerceappchallenge.data.model.Profile

interface ProfileRepository {

    fun getProfile(): LiveData<Profile>
    suspend fun getProfileData(): Profile
    suspend fun insertProfile(profile: Profile)
    suspend fun deleteAll()
    suspend fun updateEmail(email: String)
    suspend fun updatePhone(phone: String)
    suspend fun updateAddress(address: String)
    suspend fun updatePhoto(photo: Uri)
}