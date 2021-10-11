package com.example.ecommerceappchallenge.domain.usecase.profile

import android.net.Uri
import com.example.ecommerceappchallenge.domain.repository.ProfileRepository

class UpdatePhotoUseCase(private val repository: ProfileRepository) {
    suspend fun execute(photo: Uri) {
        return repository.updatePhoto(photo)
    }
}