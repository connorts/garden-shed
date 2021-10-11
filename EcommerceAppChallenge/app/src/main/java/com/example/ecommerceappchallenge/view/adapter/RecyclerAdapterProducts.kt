package com.example.ecommerceappchallenge.view.adapter

import android.transition.Slide
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceappchallenge.R
import com.example.ecommerceappchallenge.data.model.ProductsItem
import com.squareup.picasso.Picasso
import java.text.DecimalFormat


class RecyclerAdapterProducts: RecyclerView.Adapter<RecyclerAdapterProducts.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemImage: ImageView = itemView.findViewById(R.id.image_view_product_image)
        var itemTitle: TextView = itemView.findViewById(R.id.text_view_product_name)
        var itemPrice: TextView = itemView.findViewById(R.id.text_view_product_price)
        var itemButton: Button = itemView.findViewById(R.id.button_add_to_cart)
        var itemDetails: TextView = itemView.findViewById(R.id.text_view_description)
        var itemRatingCount: TextView = itemView.findViewById(R.id.text_view_rating_count)
        var itemRating: RatingBar = itemView.findViewById(R.id.rating_bar)
        var itemExpandMore: Button = itemView.findViewById(R.id.button_expand_more)
        var itemExpandLess: Button = itemView.findViewById(R.id.button_expand_less)
        var itemCategory: TextView = itemView.findViewById(R.id.text_view_category)
        var itemExpandLayout: LinearLayout = itemView.findViewById(R.id.layout_item_details)
        var itemQuantity: TextView = itemView.findViewById(R.id.text_view_item_quantity)
        var itemCard: CardView = itemView.findViewById(R.id.card_view)

        fun bindProducts(product: ProductsItem) {
            // bind product data to view components
            itemTitle.text = product.title
            itemPrice.text = "$"+ DecimalFormat("#,###.00").format(product.price)
            Picasso.get().load(product.image).into(itemImage)
            itemRatingCount.text = product.rating.count.toString()
            itemDetails.text = product.description
            itemCategory.text = product.category
            itemRating.rating = product.rating.rate.toFloat()

            // set expand button listener
            itemExpandMore.setOnClickListener {
                itemExpandLayout.visibility = View.VISIBLE
                itemExpandMore.visibility = View.GONE
            }
            // set expand less button listener
            itemExpandLess.setOnClickListener {
                itemExpandMore.visibility = View.VISIBLE
                itemExpandLayout.visibility = View.GONE
            }
            // set add to cart listener
            itemButton.setOnClickListener {
                onItemClickListener?.let {
                    it(product)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = differ.currentList[position]
        holder.bindProducts(product)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun filterList(filteredList: MutableList<ProductsItem>) {
        differ.submitList(filteredList)
    }

    // initialize click listener for adding product to cart
    private var onItemClickListener: ((ProductsItem)->Unit)?=null
    fun setItemClickListener(listener: (ProductsItem)-> Unit) {
        onItemClickListener = listener
    }

    // create item callback for AsyncListDiffer to evaluate changes in the list
    private val callback = object : DiffUtil.ItemCallback<ProductsItem>(){
        override fun areItemsTheSame(oldItem: ProductsItem, newItem: ProductsItem): Boolean {
            return oldItem.title == newItem.title
        }
        override fun areContentsTheSame(oldItem: ProductsItem, newItem: ProductsItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,callback)
}