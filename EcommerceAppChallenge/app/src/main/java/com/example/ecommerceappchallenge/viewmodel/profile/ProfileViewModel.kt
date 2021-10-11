package com.example.ecommerceappchallenge.viewmodel.profile

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceappchallenge.view.util.SingleLiveEvent
import com.example.ecommerceappchallenge.domain.usecase.profile.GetProfileUseCase
import com.example.ecommerceappchallenge.domain.usecase.profile.UpdatePhotoUseCase
import dagger.Component
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(private val getProfileUseCase: GetProfileUseCase,
                       private val updatePhotoUseCase: UpdatePhotoUseCase
) : ViewModel() {
    val profile = getProfileUseCase.execute()

    private val _navigateToUpdateProfile = SingleLiveEvent<Any>()
    val navigateToUpdateProfile: LiveData<Any>
        get() = _navigateToUpdateProfile

    private val _navigateToEditProfile = SingleLiveEvent<Int>()
    val navigateToEditProfile: LiveData<Int>
        get() = _navigateToEditProfile

    private val _chooseProfilePhoto = SingleLiveEvent<Int>()
    val chooseProfilePhoto: LiveData<Int>
        get() = _chooseProfilePhoto

    fun onUpdateProfileClicked() {
        _navigateToUpdateProfile.call()
    }

    fun onEditEmailClicked() {
        _navigateToEditProfile.value = 1
    }

    fun onEditPhoneClicked() {
        _navigateToEditProfile.value = 2
    }

    fun onEditAddressClicked() {
        _navigateToEditProfile.value = 3
    }

    fun onAddProfilePhotoClicked() {
        _chooseProfilePhoto.call()
    }

    fun updatePhoto(photo: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            updatePhotoUseCase.execute(photo)
        }
    }
}