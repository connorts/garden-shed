package com.example.ecommerceappchallenge.view.cart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceappchallenge.view.adapter.RecyclerAdapterCheckout
import com.example.ecommerceappchallenge.databinding.ActivityCheckoutBinding
import com.example.ecommerceappchallenge.viewmodel.cart.CheckoutActivityViewModel
import com.example.ecommerceappchallenge.viewmodel.cart.CheckoutActivityViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CheckoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckoutBinding
    @Inject lateinit var checkoutFactory: CheckoutActivityViewModelFactory
    private lateinit var checkoutViewModel: CheckoutActivityViewModel
    @Inject lateinit var adapterCheckout: RecyclerAdapterCheckout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkoutViewModel = ViewModelProvider(this,checkoutFactory).get(CheckoutActivityViewModel::class.java)
        binding.checkoutViewModel = checkoutViewModel
        binding.lifecycleOwner = this

        initRecyclerView()
        getCartItems()
        setUpPlaceOrderButton()

    }

    private fun initRecyclerView() {
        binding.recyclerViewCheckout.apply {
            adapter = adapterCheckout
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun getCartItems() {
        checkoutViewModel.shoppingCart.observe(this, Observer {
            adapterCheckout.differ.submitList(it)
        })
    }

    private fun setUpPlaceOrderButton() {
        checkoutViewModel.navigateToPlaceOrder.observe(this, Observer {
            val intent = Intent(this, PlaceOrderActivity::class.java)
            startActivity(intent)
        })
    }
}