package com.example.ecommerceappchallenge.data.repository.dataSource

import android.net.Uri
import androidx.lifecycle.LiveData
import com.example.ecommerceappchallenge.data.model.Profile

interface ProfileLocalDataSource {

    fun getProfile(): LiveData<Profile>
    suspend fun getProfileData(): Profile
    suspend fun insertProfile(profile: Profile)
    suspend fun deleteAll()
    suspend fun updateEmail(email: String)
    suspend fun updatePhone(phone: String)
    suspend fun updateAddress(address: String)
    suspend fun updatePhoto(photo: Uri)
}