package com.example.ecommerceappchallenge.viewmodel.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ecommerceappchallenge.domain.usecase.profile.GetProfileUseCase
import com.example.ecommerceappchallenge.domain.usecase.profile.UpdateAddressUseCase
import com.example.ecommerceappchallenge.domain.usecase.profile.UpdateEmailUseCase
import com.example.ecommerceappchallenge.domain.usecase.profile.UpdatePhoneUseCase
import kotlinx.coroutines.CoroutineDispatcher
import java.lang.IllegalArgumentException

class EditProfileViewModelFactory(private val getProfileUseCase: GetProfileUseCase,
                                  private val updateEmailUseCase: UpdateEmailUseCase,
                                  private val updatePhoneUseCase: UpdatePhoneUseCase,
                                  private val updateAddressUseCase: UpdateAddressUseCase,
                                  private val dispatcher: CoroutineDispatcher
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditProfileElementViewModel::class.java)) {
            return EditProfileElementViewModel(
                getProfileUseCase,
                updateEmailUseCase,
                updatePhoneUseCase,
                updateAddressUseCase,
                dispatcher) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}