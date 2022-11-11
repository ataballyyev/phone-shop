package com.example.phoneshop.presentation.view.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.phoneshop.R

class ProductImagesAdapter: RecyclerView.Adapter<ProductImagesAdapter.ProductImagesVH>() {

    private var listProductImages: List<String> = emptyList()
    class ProductImagesVH(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.imageViewProduct)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductImagesVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_detail_images_item, parent, false)
        return ProductImagesVH(view)
    }

    override fun onBindViewHolder(holder: ProductImagesVH, position: Int) {
        Glide.with(holder.itemView)
            .load(listProductImages[position])
            .into(holder.image)
    }

    override fun getItemCount() = listProductImages.size

    fun initializeImageList(list: List<String>) {
        listProductImages = list
        notifyDataSetChanged()
    }

}