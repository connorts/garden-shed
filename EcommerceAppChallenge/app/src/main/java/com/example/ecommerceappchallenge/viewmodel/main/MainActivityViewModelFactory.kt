package com.example.ecommerceappchallenge.viewmodel.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ecommerceappchallenge.domain.usecase.profile.GetProfileUseCase
import java.lang.IllegalArgumentException

class MainActivityViewModelFactory(private val getProfileUseCase: GetProfileUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(getProfileUseCase) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}