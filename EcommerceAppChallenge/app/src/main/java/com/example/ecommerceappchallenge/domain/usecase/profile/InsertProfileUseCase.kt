package com.example.ecommerceappchallenge.domain.usecase.profile

import com.example.ecommerceappchallenge.data.model.Profile
import com.example.ecommerceappchallenge.domain.repository.ProfileRepository

class InsertProfileUseCase(private val repository: ProfileRepository) {
    suspend fun execute(profile: Profile) {
        return repository.insertProfile(profile)
    }
}