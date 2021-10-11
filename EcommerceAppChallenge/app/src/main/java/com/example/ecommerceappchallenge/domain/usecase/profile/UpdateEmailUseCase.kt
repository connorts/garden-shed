package com.example.ecommerceappchallenge.domain.usecase.profile

import com.example.ecommerceappchallenge.domain.repository.ProfileRepository

class UpdateEmailUseCase(private val repository: ProfileRepository) {
    suspend fun execute(email: String) {
        repository.updateEmail(email)
    }
}