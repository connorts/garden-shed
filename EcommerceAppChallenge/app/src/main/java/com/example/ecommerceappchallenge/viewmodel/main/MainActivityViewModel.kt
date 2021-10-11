package com.example.ecommerceappchallenge.viewmodel.main

import androidx.lifecycle.ViewModel
import com.example.ecommerceappchallenge.domain.usecase.profile.GetProfileUseCase

class MainActivityViewModel(private val getProfileUseCase: GetProfileUseCase) : ViewModel() {

    val profile = getProfileUseCase.execute()
}