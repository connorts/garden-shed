package com.example.ecommerceappchallenge.view.products

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ecommerceappchallenge.databinding.FragmentProductsBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceappchallenge.R
import com.example.ecommerceappchallenge.data.model.ProductsItem
import com.example.ecommerceappchallenge.view.adapter.RecyclerAdapterProducts
import com.example.ecommerceappchallenge.data.util.Resource
import com.example.ecommerceappchallenge.viewmodel.products.ProductsViewModel
import com.example.ecommerceappchallenge.viewmodel.products.ProductsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductsFragment : Fragment() {

    @Inject lateinit var adapterProduct: RecyclerAdapterProducts
    private lateinit var binding: FragmentProductsBinding

    @Inject lateinit var productFactory: ProductsViewModelFactory
    private lateinit var productViewModel: ProductsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // inflate layout file for products fragment and make visible on the screen
        binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productViewModel = ViewModelProvider(this,productFactory).get(ProductsViewModel::class.java)
        binding.productsViewModel = productViewModel

        initRecyclerView()
        getProducts()
        setAddToCartListener()
        setUpToast()
        setUpSearchBar()
        setUpSearchButton()
    }

    private fun getProducts() {
        productViewModel.getProducts()
        productViewModel.products.observe(viewLifecycleOwner, Observer { response->
            when (response) {
                is Resource.Success-> {
                    hideProgressBar()
                    response.data?.let {
                        adapterProduct.differ.submitList(it)
                    }
                }
                is Resource.Loading-> {
                    showProgressBar()
                }
                is Resource.Error-> {
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity,"An error occurred: $it",Toast.LENGTH_LONG ).show()
                    }
                }
            }
        })
    }

    private fun initRecyclerView() {
        binding.recyclerViewProducts.apply {
            adapter = adapterProduct
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun setAddToCartListener() {
        adapterProduct.setItemClickListener { product->
            productViewModel.insertOrIncrease(product)
        }
    }

    private fun setUpToast() {
        productViewModel.message.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showProgressBar() {
        binding.progressCircularProducts.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressCircularProducts.visibility = View.INVISIBLE
    }

    private fun setUpSearchBar() {
        val searchBar = binding.editTextSearchBar
        searchBar.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                filter(p0.toString())
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

    private fun filter(text: String) {
        val filteredList = mutableListOf<ProductsItem>()
        for (product in adapterProduct.differ.currentList) {
            if (product.title.lowercase().contains(text.lowercase()) ||
                product.category.lowercase().contains(text.lowercase()) ||
                product.description.lowercase().contains(text.lowercase())) {
                    filteredList.add(product)
            }
        }
        adapterProduct.filterList(filteredList)
    }

    private fun setUpSearchButton() {
        binding.editTextSearchBar.visibility = View.GONE
        binding.buttonSearch.setOnClickListener {
            if (binding.buttonSearch.isChecked) {
                binding.editTextSearchBar.visibility = View.VISIBLE
                binding.editTextSearchBar.requestFocus()
                activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
            } else {
                binding.editTextSearchBar.visibility = View.GONE
                binding.editTextSearchBar.clearFocus()
                productViewModel.filterByAll()
            }
        }
    }
}