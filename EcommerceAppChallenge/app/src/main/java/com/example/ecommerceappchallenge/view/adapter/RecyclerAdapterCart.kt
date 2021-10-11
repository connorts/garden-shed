package com.example.ecommerceappchallenge.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceappchallenge.R
import com.example.ecommerceappchallenge.data.model.CartItem
import com.example.ecommerceappchallenge.data.model.ProductsItem
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

class RecyclerAdapterCart: RecyclerView.Adapter<RecyclerAdapterCart.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemImage: ImageView = itemView.findViewById(R.id.image_view_product_image)
        var itemTitle: TextView = itemView.findViewById(R.id.text_view_product_name)
        var itemPrice: TextView = itemView.findViewById(R.id.text_view_product_price)
        var itemQuantity: TextView = itemView.findViewById(R.id.text_view_items_in_cart)
        var itemButtonRemove: Button = itemView.findViewById(R.id.button_remove_from_cart)
        var itemButtonAdd: Button = itemView.findViewById(R.id.button_increase_cart_quantity)
        var itemTotalPrice: TextView = itemView.findViewById((R.id.text_view_item_total))

        fun bindItem(cartItem: CartItem, position:Int) {
            Picasso.get().load(cartItem.image).into(itemImage)
            itemTitle.text = cartItem.title
            itemPrice.text = "$"+DecimalFormat("#,###.00").format(cartItem.price)
            itemQuantity.text = cartItem.quantity.toString()
            itemTotalPrice.text = "$"+DecimalFormat("#,###.00").format(cartItem.quantity*cartItem.price)
            itemButtonRemove.setOnClickListener {
                onRemoveClickListener?.let {
                    it(cartItem)
                }
            }
            itemButtonAdd.setOnClickListener {
                onAddClickListener?.let {
                    it(cartItem)
                }
            }

        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(differ.currentList[position], position)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onRemoveClickListener: ((CartItem)->Unit)?=null
    fun setOnRemoveClickListener(listener: (CartItem)-> Unit) {
        onRemoveClickListener = listener
    }

    private var onAddClickListener: ((CartItem)->Unit)?=null
    fun setOnAddClickListener(listener: (CartItem)-> Unit) {
        onAddClickListener = listener
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