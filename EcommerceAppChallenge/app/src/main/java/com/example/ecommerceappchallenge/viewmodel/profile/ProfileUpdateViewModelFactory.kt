package com.example.ecommerceappchallenge.viewmodel.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ecommerceappchallenge.domain.usecase.profile.DeleteProfileUseCase
import com.example.ecommerceappchallenge.domain.usecase.profile.InsertProfileUseCase
import kotlinx.coroutines.CoroutineDispatcher
import java.lang.IllegalArgumentException

class ProfileUpdateViewModelFactory(private val deleteProfileUseCase: DeleteProfileUseCase,
                                    private val insertProfileUseCase: InsertProfileUseCase,
                                    private val dispatcher: CoroutineDispatcher
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileUpdateViewModel::class.java)) {
            return ProfileUpdateViewModel(deleteProfileUseCase,insertProfileUseCase,dispatcher) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}