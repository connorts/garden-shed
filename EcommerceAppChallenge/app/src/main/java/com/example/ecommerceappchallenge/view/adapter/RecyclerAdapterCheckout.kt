package com.example.ecommerceappchallenge.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceappchallenge.R
import com.example.ecommerceappchallenge.data.model.CartItem

class RecyclerAdapterCheckout: RecyclerView.Adapter<RecyclerAdapterCheckout.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemName: TextView
        var itemQuantity: TextView

        init {
            itemName = itemView.findViewById(R.id.text_view_checkout_item_name)
            itemQuantity = itemView.findViewById(R.id.text_view_checkout_item_quantity)
        }

        fun bindCheckoutItem(cartItem: CartItem) {
            itemName.text = cartItem.title
            itemQuantity.text = cartItem.quantity.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_checkout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindCheckoutItem(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private val callback = object : DiffUtil.ItemCallback<CartItem>(){
        override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem.title == newItem.title
        }
        override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,callback)
}