package com.example.phoneshop.presentation.view.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.basket.Basket
import com.example.phoneshop.R

class MyCartAdapter : RecyclerView.Adapter<MyCartAdapter.MyCartVH>() {

    private var listBasket: List<Basket> = emptyList()

    class MyCartVH(itemView: View): RecyclerView.ViewHolder(itemView) {
        var imageProduct: ImageView = itemView.findViewById(R.id.imageViewProductCart)
        var titleProduct: TextView = itemView.findViewById(R.id.textTitleProductCart)
        var priceProduct: TextView = itemView.findViewById(R.id.textPriceProductCart)
        var removeButton: ImageView = itemView.findViewById(R.id.removeButtonCart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCartVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.my_cart_item, parent, false)
        return MyCartVH(view)
    }

    override fun onBindViewHolder(holder: MyCartVH, position: Int) {
        Glide.with(holder.imageProduct)
            .load(listBasket[position].images)
            .into(holder.imageProduct)
        holder.titleProduct.text = listBasket[position].title
        holder.priceProduct.text = "$${listBasket[position].price}"
    }

    override fun getItemCount() = listBasket.size

    fun initialize(list: List<Basket>) {
        listBasket = list
        notifyDataSetChanged()
    }
}