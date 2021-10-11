package com.example.ecommerceappchallenge.view.offers

import android.os.Bundle
import android.os.CountDownTimer
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.work.*
import com.example.ecommerceappchallenge.data.model.ProductsItem
import com.example.ecommerceappchallenge.databinding.FragmentOffersBinding
import com.example.ecommerceappchallenge.view.DailyDealWorker
import com.example.ecommerceappchallenge.view.util.CartItemConverter
import com.example.ecommerceappchallenge.viewmodel.offers.OffersViewModel
import com.example.ecommerceappchallenge.viewmodel.offers.OffersViewModelFactory
import dagger.Component
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class OffersFragment : Fragment() {
    @Inject lateinit var offersFactory: OffersViewModelFactory
    private  lateinit var viewModel: OffersViewModel
    private lateinit var binding: FragmentOffersBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOffersBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this,offersFactory).get(OffersViewModel::class.java)
        binding.offersViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        initDailyDeal()
        getDailyDeal()
    }

    private fun initDailyDeal() {
        val currentDate = Calendar.getInstance()
        val dueDate = Calendar.getInstance()

        // Set Execution around 2:30:00 PM
        dueDate.set(Calendar.HOUR_OF_DAY, 14)
        dueDate.set(Calendar.MINUTE, 30)
        dueDate.set(Calendar.SECOND, 0)

        if (dueDate.before(currentDate)) {
            dueDate.add(Calendar.HOUR_OF_DAY, 24)
        }
        val timeDiff = dueDate.timeInMillis - currentDate.timeInMillis
        startCountdown(timeDiff)
        val dealRequest = OneTimeWorkRequest.Builder(DailyDealWorker::class.java)
            .setInitialDelay(timeDiff, TimeUnit.MILLISECONDS)
            .build()

        WorkManager.getInstance(requireActivity().applicationContext)
            .enqueueUniqueWork("GET_DAILY_DEAL",ExistingWorkPolicy.REPLACE,dealRequest)
    }

    private fun startCountdown(timeDiff: Long) {
        object: CountDownTimer(timeDiff,1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.textViewDealCountdown.text = DateUtils.formatElapsedTime(millisUntilFinished/1000)+" left for this deal!"
            }
            override fun onFinish() {
                binding.textViewDealCountdown.text = "deal is no longer available"
                binding.buttonAddDealToCart.visibility = View.GONE
            }
        }.start()
    }

    private fun getDailyDeal() {
        viewModel.offer.observe(viewLifecycleOwner, Observer {
            if (it!=null) {
                val deal = it.copy(price = it.price*0.8)
                binding.textViewDealNewPrice.text = "$"+DecimalFormat("#,###.00").format(deal.price)
                setAddToCartListener(deal)
            }
        })
    }

    private fun setAddToCartListener(deal: ProductsItem) {
        binding.buttonAddDealToCart.setOnClickListener {
            viewModel.addDealToCart(CartItemConverter.productToCartItem(deal))
            Toast.makeText(context,"Added to cart!",Toast.LENGTH_SHORT).show()
        }
    }
}