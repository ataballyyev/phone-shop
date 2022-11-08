package com.example.phoneshop.presentation.view.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.home.BestSeller
import com.example.phoneshop.R

class BestSellerProductsAdapter: RecyclerView.Adapter<BestSellerProductsAdapter.BestSellerProductsVH>() {

    private var listBestSeller: List<BestSeller> = emptyList()

    class BestSellerProductsVH(itemView: View): RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.imageProduct)
        var discountPrice: TextView = itemView.findViewById(R.id.textDiscountPrice)
        var withoutDiscountPrice: TextView = itemView.findViewById(R.id.textWithoutDiscountPrice)
        var titleProduct: TextView = itemView.findViewById(R.id.textTitleProduct)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellerProductsVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.best_seller_item, parent, false)
        return BestSellerProductsVH(view)
    }

    override fun onBindViewHolder(holder: BestSellerProductsVH, position: Int) {
        holder.discountPrice.text = "$${listBestSeller[position].discount_price}"
        holder.withoutDiscountPrice.text = "$${listBestSeller[position].price_without_discount}"
        holder.titleProduct.text = listBestSeller[position].title

        Glide.with(holder.image)
            .load(listBestSeller[position].picture)
            .into(holder.image)
    }

    override fun getItemCount() = listBestSeller.size

    fun initializeList(list: List<BestSeller>) {
        listBestSeller = list
        notifyDataSetChanged()
    }
}