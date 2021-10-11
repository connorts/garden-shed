package com.example.ecommerceappchallenge.domain.usecase.profile

import androidx.lifecycle.LiveData
import com.example.ecommerceappchallenge.data.model.Profile
import com.example.ecommerceappchallenge.domain.repository.ProfileRepository

class GetProfileUseCase(private val repository: ProfileRepository) {

    fun execute(): LiveData<Profile> {
        return repository.getProfile()
    }
}