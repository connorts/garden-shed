package com.example.ecommerceappchallenge.domain.usecase.profile

import com.example.ecommerceappchallenge.domain.repository.ProfileRepository

class UpdatePhoneUseCase(private val repository: ProfileRepository) {
    suspend fun execute(phone: String) {
        return repository.updatePhone(phone)
    }
}