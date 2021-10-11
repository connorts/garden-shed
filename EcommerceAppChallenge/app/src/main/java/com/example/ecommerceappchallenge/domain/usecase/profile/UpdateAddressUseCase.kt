package com.example.ecommerceappchallenge.domain.usecase.profile

import com.example.ecommerceappchallenge.domain.repository.ProfileRepository

class UpdateAddressUseCase(private val repository: ProfileRepository) {
    suspend fun execute(address: String) {
        return repository.updateAddress(address)
    }
}