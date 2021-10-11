package com.example.ecommerceappchallenge.viewmodel.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ecommerceappchallenge.domain.usecase.profile.GetProfileUseCase
import com.example.ecommerceappchallenge.domain.usecase.profile.UpdatePhotoUseCase
import java.lang.IllegalArgumentException

class ProfileViewModelFactory(private val getProfileUseCase: GetProfileUseCase,
                              private val updatePhotoUseCase: UpdatePhotoUseCase
) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(getProfileUseCase,updatePhotoUseCase) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}