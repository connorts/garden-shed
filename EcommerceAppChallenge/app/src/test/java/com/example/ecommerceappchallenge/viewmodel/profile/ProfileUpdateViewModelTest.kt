package com.example.ecommerceappchallenge.viewmodel.profile

import com.example.ecommerceappchallenge.InstantExecutorExtension
import com.example.ecommerceappchallenge.domain.repository.FakeProfileRepository
import com.example.ecommerceappchallenge.domain.usecase.profile.DeleteProfileUseCase
import com.example.ecommerceappchallenge.domain.usecase.profile.InsertProfileUseCase
import com.example.ecommerceappchallenge.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
internal class ProfileUpdateViewModelTest {

    private lateinit var viewModel: ProfileUpdateViewModel
    private val dispatcher = TestCoroutineDispatcher()

    @BeforeEach
    fun setUp() {
        val fakeProfileRepository = FakeProfileRepository()
        val deleteProfileUseCase = DeleteProfileUseCase(fakeProfileRepository)
        val insertProfileUseCase = InsertProfileUseCase(fakeProfileRepository)
        viewModel = ProfileUpdateViewModel(deleteProfileUseCase,insertProfileUseCase,dispatcher)
    }

//    @Test
//    fun getNavigateToMainTest() {
//    }

//    @Test
//    fun getMessage() {
//    }

    @Test
    fun getInputFirstNameTest() {
        viewModel.inputFirstName.value = "first"
        val name = viewModel.inputFirstName.getOrAwaitValue()
        assertThat(name).isEqualTo("first")
    }

    @Test
    fun getInputLastNameTest() {
        viewModel.inputLastName.value = "last"
        val name = viewModel.inputLastName.getOrAwaitValue()
        assertThat(name).isEqualTo("last")
    }

    @Test
    fun getInputEmailTest() {
        viewModel.inputEmail.value = "email"
        val email = viewModel.inputEmail.getOrAwaitValue()
        assertThat(email).isEqualTo("email")
    }

    @Test
    fun getInputPhoneTest() {
        viewModel.inputPhone.value = "phone"
        val phone = viewModel.inputPhone.getOrAwaitValue()
        assertThat(phone).isEqualTo("phone")
    }

    @Test
    fun getInputAddressTest() {
        viewModel.inputAddress.value = "address"
        val address = viewModel.inputAddress.getOrAwaitValue()
        assertThat(address).isEqualTo("address")
    }

    @Test
    fun getInputPasswordTest() {
        viewModel.inputPassword.value = "password"
        val password = viewModel.inputPassword.getOrAwaitValue()
        assertThat(password).isEqualTo("password")
    }

//    @Test
//    fun onSaveProfileClicked() {
//        // assert error message when fields are empty
//        // assert error message when input is invalid
//        // assert profile is new profile when all conditions are met
//    }

}