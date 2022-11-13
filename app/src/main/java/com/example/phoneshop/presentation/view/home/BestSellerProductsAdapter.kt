package com.example.phoneshop.presentation.view.home

import android.annotation.SuppressLint
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
    private var likedProducts: ArrayList<Boolean> = arrayListOf()
    private lateinit var onItemClickListener: OnItemClickListener

    inner class BestSellerProductsVH(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var image: ImageView = itemView.findViewById(R.id.imageProduct)
        var discountPrice: TextView = itemView.findViewById(R.id.textDiscountPrice)
        var withoutDiscountPrice: TextView = itemView.findViewById(R.id.textWithoutDiscountPrice)
        var titleProduct: TextView = itemView.findViewById(R.id.textTitleProduct)
        private var likeButton: ImageView = itemView.findViewById(R.id.imageViewLikeButton)

        init {
            itemView.setOnClickListener(this)
            likeButton.setOnClickListener {
                if (!likedProducts[adapterPosition]) {
                    likeButton.setImageDrawable(likeButton.context.resources.getDrawable(R.drawable.like))
                    likedProducts[adapterPosition] = true
                } else {
                    likeButton.setImageDrawable(likeButton.context.resources.getDrawable(R.drawable.unlike))
                    likedProducts[adapterPosition] = false
                }
            }
        }

        override fun onClick(p0: View?) {
            onItemClickListener.onClick(adapterPosition)
        }
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

    @SuppressLint("NotifyDataSetChanged")
    fun initializeList(list: List<BestSeller>) {
        listBestSeller = list
        repeat(listBestSeller.size) {
            likedProducts.add(false)
        }
        notifyDataSetChanged()
    }

    fun initializeOnClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    interface OnItemClickListener {
        fun onClick(position: Int)
    }
}