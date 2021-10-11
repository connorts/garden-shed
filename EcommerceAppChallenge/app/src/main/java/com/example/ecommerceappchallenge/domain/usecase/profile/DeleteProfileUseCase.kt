package com.example.ecommerceappchallenge.domain.usecase.profile

import com.example.ecommerceappchallenge.domain.repository.ProfileRepository

class DeleteProfileUseCase(private val repository: ProfileRepository) {
    suspend fun execute() {
        return repository.deleteAll()
    }
}