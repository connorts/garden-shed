package com.example.ecommerceappchallenge.domain.repository

import android.content.ContentResolver
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.ecommerceappchallenge.data.model.Profile

class FakeProfileRepository: ProfileRepository {
    private val profiles = mutableListOf<Profile>()

    init {
        profiles.add(Profile(1,"fname1","lname1","email1","phone1","address1","password1", Uri.EMPTY))
    }

    override fun getProfile(): LiveData<Profile> {
        return liveData { emit(profiles[0]) }
    }

    override suspend fun getProfileData(): Profile {
        return profiles[0]
    }

    override suspend fun insertProfile(profile: Profile) {
        profiles.add(profile)
    }

    override suspend fun deleteAll() {
        profiles.clear()
    }

    override suspend fun updateEmail(email: String) {
        profiles[0].email = email
    }

    override suspend fun updatePhone(phone: String) {
        profiles[0].phone = phone
    }

    override suspend fun updateAddress(address: String) {
        profiles[0].address = address
    }

    override suspend fun updatePhoto(photo: Uri) {
        profiles[0].photo = photo
    }
}