package com.example.ecommerceappchallenge.domain.usecase.profile

import com.example.ecommerceappchallenge.data.model.Profile
import com.example.ecommerceappchallenge.domain.repository.ProfileRepository

class GetProfileDataUseCase(private val repository: ProfileRepository) {
    suspend fun execute(): Profile {
        return repository.getProfileData()
    }
}