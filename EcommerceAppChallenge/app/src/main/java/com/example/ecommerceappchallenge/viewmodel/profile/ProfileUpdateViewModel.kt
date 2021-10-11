package com.example.ecommerceappchallenge.viewmodel.profile

import android.content.ContentResolver
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceappchallenge.view.util.SingleLiveEvent
import com.example.ecommerceappchallenge.data.model.Profile
import com.example.ecommerceappchallenge.domain.usecase.profile.DeleteProfileUseCase
import com.example.ecommerceappchallenge.domain.usecase.profile.InsertProfileUseCase
import com.example.ecommerceappchallenge.view.util.Event
import com.wajahatkarim3.easyvalidation.core.view_ktx.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileUpdateViewModel(private val deleteProfileUseCase: DeleteProfileUseCase,
                             private val insertProfileUseCase: InsertProfileUseCase,
                             private val dispatcher: CoroutineDispatcher = Dispatchers.IO) : ViewModel() {

    private val _navigateToMain = SingleLiveEvent<Any>()
    val navigateToMain: LiveData<Any>
        get() = _navigateToMain

    private val statusMessage = MutableLiveData<Event<String>>()
    val message : LiveData<Event<String>>
        get() = statusMessage

    private val defaultPhoto = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
            "://com.example.ecommerceappchallenge/drawable/grass")

    val inputFirstName = MutableLiveData<String>()
    val inputLastName = MutableLiveData<String>()
    val inputEmail = MutableLiveData<String>()
    val inputPhone = MutableLiveData<String>()
    val inputAddress = MutableLiveData<String>()
    val inputPassword = MutableLiveData<String>()

    fun onSaveProfileClicked() {
        // checks if user input is valid
        if (inputFirstName.value!=null &&
            inputLastName.value!=null &&
            inputEmail.value!=null &&
            inputPhone.value!=null &&
            inputAddress.value!=null &&
            inputPassword.value!=null) {
            if (inputEmail.value!!.validEmail() &&
                inputPhone.value!!.validator()
                    .validNumber()
                    .minLength(7)
                    .maxLength(11)
                    .check()
            ) {
                val profile = Profile(
                    0,
                    inputFirstName.value!!,
                    inputLastName.value!!,
                    inputEmail.value!!,
                    inputPhone.value!!,
                    inputAddress.value!!,
                    inputPassword.value!!,
                    defaultPhoto)
                deleteAll()
                insert(profile)
                _navigateToMain.call()
            } else { // shows error message if input is invalid
                inputEmail.value!!.validEmail() {
                    statusMessage.value = Event("Please enter valid email address to continue")
                }
                inputPhone.value!!.validNumber() {
                    statusMessage.value = Event("Please enter valid phone number to continue")
                }
                inputPhone.value!!.minLength(7) {
                    statusMessage.value = Event("Please enter valid phone number to continue")
                }
                inputPhone.value!!.maxLength(10) {
                    statusMessage.value = Event("Please enter valid phone number to continue")
                }
            }
        } else {statusMessage.value = Event("Please complete all fields to continue")}
    }

    private fun insert(profile: Profile) {
        viewModelScope.launch(dispatcher) {
            insertProfileUseCase.execute(profile)
        }
    }

    private fun deleteAll() {
        viewModelScope.launch(dispatcher) {
            deleteProfileUseCase.execute()
        }
    }


}