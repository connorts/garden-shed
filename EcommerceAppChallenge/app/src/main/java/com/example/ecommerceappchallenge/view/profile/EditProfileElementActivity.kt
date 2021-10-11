package com.example.ecommerceappchallenge.view.profile

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ecommerceappchallenge.databinding.ActivityEditProfileElementBinding
import com.example.ecommerceappchallenge.view.main.MainActivity
import com.example.ecommerceappchallenge.viewmodel.profile.EditProfileElementViewModel
import com.example.ecommerceappchallenge.viewmodel.profile.EditProfileViewModelFactory
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EditProfileElementActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileElementBinding
    private lateinit var editProfileViewModel: EditProfileElementViewModel
    @Inject lateinit var editFactory: EditProfileViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileElementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editProfileViewModel = ViewModelProvider(this,editFactory).get(EditProfileElementViewModel::class.java)
        binding.editProfileViewModel = editProfileViewModel

        setElementInfo()
        setReturnToMainObserver()
        setUpInputValidatorMessage()
    }

    private fun setElementInfo() {
        binding.textViewEditField.text = intent.getStringExtra("Element Name")
        editProfileViewModel.intentElement.value = intent.getStringExtra("Element Name")
    }

    private fun setReturnToMainObserver() {
        editProfileViewModel.navigateToMain.observe(this, Observer {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("Fragment","Profile")
            startActivity(intent)
        })
    }

    private fun setUpInputValidatorMessage() {
        editProfileViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                val snackbar = Snackbar.make(binding.buttonSaveChanges,it, Snackbar.LENGTH_LONG)
                snackbar.setTextColor(Color.RED)
                snackbar.show()
            }
        })
    }
}