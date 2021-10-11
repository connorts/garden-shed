package com.example.ecommerceappchallenge.data.repository.dataSourceImpl

import android.net.Uri
import androidx.lifecycle.LiveData
import com.example.ecommerceappchallenge.data.local.ProfileDAO
import com.example.ecommerceappchallenge.data.model.Profile
import com.example.ecommerceappchallenge.data.repository.dataSource.ProfileLocalDataSource

class ProfileLocalDataSourceImpl(private val dao: ProfileDAO): ProfileLocalDataSource {
    override fun getProfile(): LiveData<Profile> {
        return  dao.getProfile()
    }

    override suspend fun getProfileData(): Profile {
        return dao.getProfileData()
    }

    override suspend fun insertProfile(profile: Profile) {
        return dao.insertProfile(profile)
    }

    override suspend fun deleteAll() {
        return dao.deleteAll()
    }

    override suspend fun updateEmail(email: String) {
        return dao.updateEmail(email)
    }

    override suspend fun updatePhone(phone: String) {
        return dao.updatePhone(phone)
    }

    override suspend fun updateAddress(address: String) {
        return dao.updateAddress(address)
    }

    override suspend fun updatePhoto(photo: Uri) {
        return dao.updatePhoto(photo)
    }
}