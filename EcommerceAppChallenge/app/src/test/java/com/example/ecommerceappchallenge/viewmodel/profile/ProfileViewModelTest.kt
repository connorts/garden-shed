package com.example.ecommerceappchallenge.viewmodel.profile

import android.content.ContentResolver
import android.net.Uri
import com.example.ecommerceappchallenge.InstantExecutorExtension
import com.example.ecommerceappchallenge.data.model.Profile
import com.example.ecommerceappchallenge.domain.repository.FakeProfileRepository
import com.example.ecommerceappchallenge.domain.usecase.profile.GetProfileUseCase
import com.example.ecommerceappchallenge.domain.usecase.profile.UpdatePhotoUseCase
import com.example.ecommerceappchallenge.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class,InstantExecutorExtension::class)
internal class ProfileViewModelTest {

    private lateinit var viewModel: ProfileViewModel
    private val fakeProfile = Profile(1,"fname1","lname1","email1","phone1","address1","password1", Uri.EMPTY)
    private val testPhoto = Uri.parse(
        ContentResolver.SCHEME_ANDROID_RESOURCE +
            "://com.example.ecommerceappchallenge/drawable/grass")



    @BeforeEach
    fun setup() {
        val fakeProfileRepository = FakeProfileRepository()
        val getProfileUseCase = GetProfileUseCase(fakeProfileRepository)
        val updatePhotoUseCase = UpdatePhotoUseCase(fakeProfileRepository)
        viewModel = ProfileViewModel(getProfileUseCase,updatePhotoUseCase)
    }

    @Test
    fun getProfileTest() = runBlocking {
        val profile = viewModel.profile.getOrAwaitValue()
        assertThat(profile).isEqualTo(fakeProfile)
    }

//    @Test
//    fun getChooseProfilePhotoTest() {
//
//    }

//    @Test
//    fun onUpdateProfileClickedTest() {
//        Mockito.mock(Observer<Any>)
//        viewModel.onUpdateProfileClicked()
//        val result = viewModel.navigateToUpdateProfile.getOrAwaitValue()
//    }

    @Test
    fun onEditEmailClickedTest() {
        viewModel.onEditEmailClicked()
        val navCode = viewModel.navigateToEditProfile.getOrAwaitValue()
        assertThat(navCode).isEqualTo(1)
    }

    @Test
    fun onEditPhoneClickedTest() {
        viewModel.onEditPhoneClicked()
        val navCode = viewModel.navigateToEditProfile.getOrAwaitValue()
        assertThat(navCode).isEqualTo(2)
    }

    @Test
    fun onEditAddressClickedTest() {
        viewModel.onEditAddressClicked()
        val navCode = viewModel.navigateToEditProfile.getOrAwaitValue()
        assertThat(navCode).isEqualTo(3)
    }

//    @Test
//    fun onAddProfilePhotoClickedTest() {
//    }

//    @Test
//    fun updatePhotoTest() = runBlocking {
//        viewModel.updatePhoto(testPhoto)
//        val profile = viewModel.profile.getOrAwaitValue()
//        assertThat(profile.photo).isNotNull()
//        assertThat(profile.photo).isEqualTo(fakeProfile)
//    }
}