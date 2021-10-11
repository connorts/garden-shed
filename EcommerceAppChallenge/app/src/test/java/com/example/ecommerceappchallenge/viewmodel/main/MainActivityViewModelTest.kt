package com.example.ecommerceappchallenge.viewmodel.main

import android.net.Uri
import com.example.ecommerceappchallenge.InstantExecutorExtension
import com.example.ecommerceappchallenge.data.model.Profile
import com.example.ecommerceappchallenge.domain.repository.FakeProfileRepository
import com.example.ecommerceappchallenge.domain.usecase.profile.GetProfileUseCase
import com.example.ecommerceappchallenge.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
class MainActivityViewModelTest {

    private lateinit var viewModel: MainActivityViewModel

    @BeforeEach
    fun setUp() {
        val fakeProfileRepository = FakeProfileRepository()
        val getProfileUseCase = GetProfileUseCase(fakeProfileRepository)
        viewModel = MainActivityViewModel(getProfileUseCase)
    }

    @Test
    fun getProfile() {
        val profile = viewModel.profile.getOrAwaitValue()
        assertThat(profile).isEqualTo(Profile(1,"fname1","lname1","email1","phone1","address1","password1",
            Uri.EMPTY))
    }
}