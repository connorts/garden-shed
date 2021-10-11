package com.example.ecommerceappchallenge.data.repository.impl

import android.net.Uri
import androidx.lifecycle.LiveData
import com.example.ecommerceappchallenge.data.model.Profile
import com.example.ecommerceappchallenge.data.repository.dataSource.ProfileLocalDataSource
import com.example.ecommerceappchallenge.domain.repository.ProfileRepository

class ProfileRepositoryImpl(private val profileLocalDataSource: ProfileLocalDataSource):
    ProfileRepository {
    override fun getProfile(): LiveData<Profile> {
        return profileLocalDataSource.getProfile()
    }

    override suspend fun getProfileData(): Profile {
        return profileLocalDataSource.getProfileData()
    }

    override suspend fun insertProfile(profile: Profile) {
        return profileLocalDataSource.insertProfile(profile)
    }

    override suspend fun deleteAll() {
        return profileLocalDataSource.deleteAll()
    }

    override suspend fun updateEmail(email: String) {
        return profileLocalDataSource.updateEmail(email)
    }

    override suspend fun updatePhone(phone: String) {
        return profileLocalDataSource.updatePhone(phone)
    }

    override suspend fun updateAddress(address: String) {
        return profileLocalDataSource.updateAddress(address)
    }

    override suspend fun updatePhoto(photo: Uri) {
        return profileLocalDataSource.updatePhoto(photo)
    }
}