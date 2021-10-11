package com.example.ecommerceappchallenge.domain.usecase.offers

import com.example.ecommerceappchallenge.domain.repository.OffersRepository

class DeleteOffersUseCase(private val repository: OffersRepository) {
    suspend fun execute() {
        return repository.deleteAll()
    }
}