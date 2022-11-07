package com.example.phoneshop.presentation.view.home

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.domain.model.home.HomeStore
import com.example.phoneshop.R
import com.smarteist.autoimageslider.SliderViewAdapter

class ImageSliderAdapter: SliderViewAdapter<ImageSliderAdapter.ImageSliderVH>() {

    private var listHotSales: List<HomeStore> = emptyList()

    class ImageSliderVH(itemView: View): SliderViewAdapter.ViewHolder(itemView) {
        var nameTitle: TextView = itemView.findViewById(R.id.textViewNameProduct)
        var description: TextView = itemView.findViewById(R.id.textViewDescription)
        var constraintLayout: ConstraintLayout = itemView.findViewById(R.id.constraintLayout)
    }

    override fun getCount() = listHotSales.size

    override fun onCreateViewHolder(parent: ViewGroup): ImageSliderVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_slider_item, parent, false)
        return ImageSliderVH(view)
    }

    override fun onBindViewHolder(viewHolder: ImageSliderVH, position: Int) {
        viewHolder.nameTitle.text = listHotSales[position].title
        viewHolder.description.text = listHotSales[position].subtitle

        Glide.with(viewHolder.itemView)
            .load(listHotSales[position].picture)
            .into(object : CustomTarget<Drawable>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    viewHolder.constraintLayout.background = resource
                }

                override fun onLoadCleared(placeholder: Drawable?) {

                }
            })
    }

    private fun initializeList(list: List<HomeStore>) {
        listHotSales = list
        notifyDataSetChanged()
    }
}