package com.example.ecommerceappchallenge.view.cart

import android.content.Intent
import android.graphics.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.ecommerceappchallenge.databinding.FragmentCartBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceappchallenge.R
import com.example.ecommerceappchallenge.view.adapter.RecyclerAdapterCart
import com.example.ecommerceappchallenge.view.profile.ProfileUpdateActivity
import com.example.ecommerceappchallenge.viewmodel.cart.CartItemViewModel
import com.example.ecommerceappchallenge.viewmodel.cart.CartItemViewModelFactory
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator

@AndroidEntryPoint
class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    @Inject lateinit var cartFragFactory: CartItemViewModelFactory
    private lateinit var cartViewModel: CartItemViewModel
    @Inject lateinit var adapterCart: RecyclerAdapterCart

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cartViewModel = ViewModelProvider(this,cartFragFactory).get(CartItemViewModel::class.java)
        binding.cartViewModel = cartViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        initRecyclerView()
        getShoppingCart()
        setClickListeners()
        setSwipeToDelete()
        setProfileValidationSnackbar()
        checkIfCartEmpty()
        setUpCheckoutButton()

        binding.progressCircularCart.visibility = View.INVISIBLE
    }

    private fun getShoppingCart() {
        cartViewModel.shoppingCart.observe(viewLifecycleOwner, Observer {
            adapterCart.differ.submitList(it)
        })
    }

    private fun initRecyclerView() {
        binding.recyclerViewCart.apply {
            adapter = adapterCart
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun setClickListeners() {
        adapterCart.setOnRemoveClickListener { item ->
            cartViewModel.removeOrDecrease(item)
        }
        adapterCart.setOnAddClickListener { item ->
            cartViewModel.increaseQuantity(item.id)
        }
    }

    private fun setSwipeToDelete() {
        val itemTouchHelperCallback = object: ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.bindingAdapterPosition
                val item = adapterCart.differ.currentList[position]
                cartViewModel.delete(item)
                Toast.makeText(context,"Deleted successfully",Toast.LENGTH_SHORT).show()
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {

                RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addBackgroundColor(ContextCompat.getColor(context!!, R.color.red))
                    .addActionIcon(R.drawable.ic_delete_24)
                    .setActionIconTint(R.color.white)
                    .addSwipeLeftLabel("Delete")
                    .setSwipeLeftLabelColor(R.color.white)
                    .create()
                    .decorate()

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        }
        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.recyclerViewCart)
        }
    }

    private fun setProfileValidationSnackbar() {
        cartViewModel.message.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                val snackbar = Snackbar.make(binding.relativeLayoutCheckoutBanner,it, Snackbar.LENGTH_LONG)
                snackbar.setAction("Create", View.OnClickListener {
                    val intent = Intent(context,ProfileUpdateActivity::class.java)
                    startActivity(intent)
                })
                snackbar.setActionTextColor(Color.BLUE)
                snackbar.show()
            }
        })
    }

    private fun checkIfCartEmpty() {
        cartViewModel.shoppingCart.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) {
                binding.relativeLayoutCheckoutBanner.visibility = View.INVISIBLE
                binding.textViewCartEmpty.visibility = View.VISIBLE
                binding.textViewShoppingCartTitle.visibility = View.INVISIBLE
                binding.viewCartTitleUnderline.visibility = View.INVISIBLE
            } else {
                binding.relativeLayoutCheckoutBanner.visibility = View.VISIBLE
                binding.textViewCartEmpty.visibility = View.INVISIBLE
                binding.textViewShoppingCartTitle.visibility = View.VISIBLE
                binding.viewCartTitleUnderline.visibility = View.VISIBLE
            }
        })
    }

    private fun setUpCheckoutButton() {
        cartViewModel.navigateToCheckout.observe(viewLifecycleOwner, Observer{
            val intent = Intent(context, CheckoutActivity::class.java)
            startActivity(intent)
        })
    }
}