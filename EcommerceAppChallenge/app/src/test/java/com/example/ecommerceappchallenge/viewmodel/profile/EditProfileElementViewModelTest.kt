package com.example.ecommerceappchallenge.viewmodel.profile

import android.net.Uri
import com.example.ecommerceappchallenge.InstantExecutorExtension
import com.example.ecommerceappchallenge.data.model.Profile
import com.example.ecommerceappchallenge.domain.repository.FakeProfileRepository
import com.example.ecommerceappchallenge.domain.usecase.profile.GetProfileUseCase
import com.example.ecommerceappchallenge.domain.usecase.profile.UpdateAddressUseCase
import com.example.ecommerceappchallenge.domain.usecase.profile.UpdateEmailUseCase
import com.example.ecommerceappchallenge.domain.usecase.profile.UpdatePhoneUseCase
import com.example.ecommerceappchallenge.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
internal class EditProfileElementViewModelTest {

    private lateinit var viewModel: EditProfileElementViewModel
    private val fakeProfile = Profile(1,"fname1","lname1","email1","phone1","address1","password1", Uri.EMPTY)
    private val dispatcher = TestCoroutineDispatcher()

    @BeforeEach
    fun setUp() {
        val fakeProfileRepository = FakeProfileRepository()
        val getProfileUseCase = GetProfileUseCase(fakeProfileRepository)
        val updateEmailUseCase = UpdateEmailUseCase(fakeProfileRepository)
        val updatePhoneUseCase = UpdatePhoneUseCase(fakeProfileRepository)
        val updateAddressUseCase = UpdateAddressUseCase(fakeProfileRepository)
        viewModel = EditProfileElementViewModel(getProfileUseCase,updateEmailUseCase,updatePhoneUseCase,updateAddressUseCase,dispatcher)
    }

    @Test
    fun getProfileTest() = runBlocking {
        val profile = viewModel.profile.getOrAwaitValue()
        assertThat(profile).isEqualTo(fakeProfile)
    }

    @Test
    fun getInput() {
        viewModel.input.value = "test input"
        val input = viewModel.input.getOrAwaitValue()
        assertThat(input).isEqualTo("test input")
    }

    @Test
    fun getIntentElement() {
        viewModel.intentElement.value = "test intent"
        val intent = viewModel.intentElement.getOrAwaitValue()
        assertThat(intent).isEqualTo("test intent")
    }

//    @Test
//    fun setIntentElement() {
//    }
//
//    @Test
//    fun getNavigateToMain() {
//    }
//
//    @Test
//    fun getMessage() {
//        viewModel.message.value =
//    }

    @Test
    fun onSaveButtonCLicked_nullInput_setStatusMessage() {
        viewModel.intentElement.value = "Edit email"
        viewModel.onSaveButtonClicked()
        val message = viewModel.message.getOrAwaitValue().getContentIfNotHandled()
        assertThat(message).isEqualTo("Can't be empty")
    }

    @Test
    fun onSaveButtonClicked_invalidEmail_setStatusMessage() {
        viewModel.input.value = "invalid"
        viewModel.intentElement.value = "Edit email"
        viewModel.onSaveButtonClicked()
        val message = viewModel.message.getOrAwaitValue().getContentIfNotHandled()
        assertThat(message).isEqualTo("Please enter a valid email")
    }

    @Test
    fun onSaveButtonClicked_invalidPhoneNumber_setStatusMessage() {
        viewModel.input.value = "invalid"
        viewModel.intentElement.value = "Edit phone number"
        viewModel.onSaveButtonClicked()
        val message = viewModel.message.getOrAwaitValue().getContentIfNotHandled()
        assertThat(message).isEqualTo("Please enter a valid phone number")
    }

    @Test
    fun onSaveButtonClicked_validEmail_emailUpdated() {
        viewModel.input.value = "valid@email.com"
        viewModel.intentElement.value = "Edit email"
        viewModel.onSaveButtonClicked()
        val profile = viewModel.profile.getOrAwaitValue()
        assertThat(profile.email).isEqualTo("valid@email.com")
    }

    @Test
    fun onSaveButtonClicked_validPhoneNumber_phoneNumberUpdated() {
        viewModel.input.value = "1234567"
        viewModel.intentElement.value = "Edit phone number"
        viewModel.onSaveButtonClicked()
        val profile = viewModel.profile.getOrAwaitValue()
        assertThat(profile.phone).isEqualTo("1234567")
    }

    @Test
    fun onSaveButtonClicked_validAddress_addressUpdated() {
        viewModel.input.value = "valid address"
        viewModel.intentElement.value = "Edit address"
        viewModel.onSaveButtonClicked()
        val profile = viewModel.profile.getOrAwaitValue()
        assertThat(profile.address).isEqualTo("valid address")
    }
}