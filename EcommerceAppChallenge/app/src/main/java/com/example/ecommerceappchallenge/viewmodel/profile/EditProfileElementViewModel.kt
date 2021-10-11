package com.example.ecommerceappchallenge.viewmodel.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceappchallenge.view.util.SingleLiveEvent
import com.example.ecommerceappchallenge.domain.usecase.profile.GetProfileUseCase
import com.example.ecommerceappchallenge.domain.usecase.profile.UpdateAddressUseCase
import com.example.ecommerceappchallenge.domain.usecase.profile.UpdateEmailUseCase
import com.example.ecommerceappchallenge.domain.usecase.profile.UpdatePhoneUseCase
import com.example.ecommerceappchallenge.view.util.Event
import com.wajahatkarim3.easyvalidation.core.view_ktx.validEmail
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditProfileElementViewModel(private val getProfileUseCase: GetProfileUseCase,
                                  private val updateEmailUseCase: UpdateEmailUseCase,
                                  private val updatePhoneUseCase: UpdatePhoneUseCase,
                                  private val updateAddressUseCase: UpdateAddressUseCase,
                                  private val dispatcher: CoroutineDispatcher = Dispatchers.IO): ViewModel() {

    val profile = getProfileUseCase.execute()
    val input = MutableLiveData<String>()
    var intentElement = MutableLiveData<String>()

    private val _navigateToMain = SingleLiveEvent<Any>()
    val navigateToMain: LiveData<Any>
        get() = _navigateToMain

    private val statusMessage = MutableLiveData<Event<String>>()
    val message : LiveData<Event<String>>
        get() = statusMessage

    fun onSaveButtonClicked() {
        if (input.value!=null) {
            when (intentElement.value) {
                "Edit email" -> saveEmail()
                "Edit phone number" -> savePhoneNumber()
                "Edit address" -> saveAddress()
            }
        } else {statusMessage.value = Event("Can't be empty")}
    }

    private fun saveEmail() {
        if (input.value!!.validEmail()) {
            updateEmail(input.value!!)
            _navigateToMain.call()
        } else {statusMessage.value = Event("Please enter a valid email")}
    }

    private fun savePhoneNumber() {
        if (input.value!!.validator()
                .validNumber()
                .minLength(7)
                .maxLength(10)
                .check()) {
            updatePhone(input.value!!)
            _navigateToMain.call()
        } else {statusMessage.value = Event("Please enter a valid phone number")}
    }

    private fun saveAddress() {
        updateAddress(input.value!!)
        _navigateToMain.call()
    }

    private fun updateEmail(email: String) {
        viewModelScope.launch(dispatcher) {
            updateEmailUseCase.execute(email)
        }
    }

    private fun updatePhone(phone: String) {
        viewModelScope.launch(dispatcher) {
            updatePhoneUseCase.execute(phone)
        }
    }

    private fun updateAddress(address: String) {
        viewModelScope.launch(dispatcher) {
            updateAddressUseCase.execute(address)
        }
    }
}