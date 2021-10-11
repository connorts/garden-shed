package com.example.ecommerceappchallenge.view.profile

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ecommerceappchallenge.databinding.ActivityProfileUpdateBinding
import com.example.ecommerceappchallenge.view.main.MainActivity
import com.example.ecommerceappchallenge.viewmodel.profile.ProfileUpdateViewModel
import com.example.ecommerceappchallenge.viewmodel.profile.ProfileUpdateViewModelFactory
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileUpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileUpdateBinding
    private lateinit var profileUpdateViewModel: ProfileUpdateViewModel
    @Inject lateinit var updateFactory: ProfileUpdateViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        profileUpdateViewModel = ViewModelProvider(this,updateFactory).get(ProfileUpdateViewModel::class.java)
        binding.profileUpdateViewModel = profileUpdateViewModel

        setUpCreateProfileObserver()
        setUpInputValidationToast()
    }

    private fun setUpCreateProfileObserver() {
        profileUpdateViewModel.navigateToMain.observe(this, Observer {
            intent = Intent(this, MainActivity::class.java)
            intent.putExtra("Fragment","Profile")
            startActivity(intent)
        })
    }

    private fun setUpInputValidationToast() {
        profileUpdateViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                val snackbar = Snackbar.make(binding.buttonCreateProfile,it,Snackbar.LENGTH_LONG)
                snackbar.setTextColor(Color.RED)
                snackbar.show()
            }
        })
    }
}
