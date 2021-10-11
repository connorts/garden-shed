package com.example.ecommerceappchallenge.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ecommerceappchallenge.R
import com.example.ecommerceappchallenge.databinding.ActivityMainBinding
import com.example.ecommerceappchallenge.view.cart.CartFragment
import com.example.ecommerceappchallenge.view.offers.OffersFragment
import com.example.ecommerceappchallenge.view.products.ProductsFragment
import com.example.ecommerceappchallenge.view.profile.ProfileFragment
import com.example.ecommerceappchallenge.view.profile.ProfileUpdateActivity
import com.example.ecommerceappchallenge.viewmodel.main.MainActivityViewModel
import com.example.ecommerceappchallenge.viewmodel.main.MainActivityViewModelFactory
import com.google.android.material.navigation.NavigationBarView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    @Inject lateinit var mainFactory: MainActivityViewModelFactory

    private lateinit var mainViewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // use view binding to inflate activity_main layout to screen
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // init main view model
        mainViewModel = ViewModelProvider(this,mainFactory).get(MainActivityViewModel::class.java)
        binding.mainViewModel = mainViewModel

        // pass main activity into set on item selected method, implements interface
        binding.bottomNav.setOnItemSelectedListener(this)

        checkLastLocation()
    }

    private fun checkLastLocation() {
        when (intent.getStringExtra("Fragment")) {
            "Profile" -> supportFragmentManager.commit {
                replace(R.id.frame_content, ProfileFragment())
            }
            else -> supportFragmentManager.commit {
                replace(R.id.frame_content, ProductsFragment())
            }
        }
    }

    private fun onProductsClicked() {
        supportFragmentManager.commit {
            replace(R.id.frame_content, ProductsFragment())
        }
    }

    private fun onOffersClicked() {
        supportFragmentManager.commit {
            replace(R.id.frame_content, OffersFragment())
        }
    }

    private fun onCartClicked() {
        supportFragmentManager.commit {
            replace(R.id.frame_content, CartFragment())
        }
    }

    private fun onProfileClicked() {
        mainViewModel.profile.observe(this, Observer {
            if (it != null) {
                supportFragmentManager.commit {
                    replace(R.id.frame_content, ProfileFragment())
                }
            } else {
                val intent = Intent(this, ProfileUpdateActivity::class.java)
                startActivity(intent)
            }
        })
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.nav_offers) {
            onOffersClicked()
            return true
        } else if (item.itemId == R.id.nav_cart) {
            onCartClicked()
            return true
        } else if (item.itemId == R.id.nav_profile) {
            onProfileClicked()
            return true
        } else if (item.itemId == R.id.nav_products) {
            onProductsClicked()
            return true
        } else {
            return false
        }
    }
}