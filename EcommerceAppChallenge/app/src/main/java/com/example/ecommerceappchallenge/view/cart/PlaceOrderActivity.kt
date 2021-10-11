package com.example.ecommerceappchallenge.view.cart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ecommerceappchallenge.databinding.ActivityPlaceOrderBinding
import com.example.ecommerceappchallenge.view.main.MainActivity
import com.example.ecommerceappchallenge.viewmodel.cart.PlaceOrderActivityViewModel

class PlaceOrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlaceOrderBinding
    private lateinit var placeOrderViewModel: PlaceOrderActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlaceOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        placeOrderViewModel = ViewModelProvider(this).get(PlaceOrderActivityViewModel::class.java)
        binding.placeOrderViewModel = placeOrderViewModel
        binding.lifecycleOwner = this

        keepShopping()
    }

    private fun keepShopping() {
        placeOrderViewModel.navigateToMain.observe(this, Observer {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })
    }
}