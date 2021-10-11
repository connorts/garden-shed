package com.example.ecommerceappchallenge.view.adapter

import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

object CurrencyBindingAdapter {
    @BindingAdapter("currency")
    @JvmStatic
    fun bindCurrency(view: TextView, amount: Double){
        val dec = DecimalFormat("#,###.00")
        view.text = "$"+dec.format(amount)
    }
}

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (url != null) {
        Picasso.get().load(url).into(view)
    }
}

@BindingAdapter("ratingDouble")
fun convert(view: RatingBar, rate: Double) {
    view.rating = rate.toFloat()
}
