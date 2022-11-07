package com.example.phoneshop.presentation.view.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.CategoryModel
import com.example.phoneshop.R

class CategoryAdapter: RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var categories: List<CategoryModel> = emptyList()
    private lateinit var itemClickListener: OnItemClickListener
    private var selectedItem = 0

    inner class CategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var image: ImageView = itemView.findViewById(R.id.imageCategory)
        var title: TextView = itemView.findViewById(R.id.textTitleCategory)

        init {
            this.itemView.setOnClickListener(this)
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun onClick(p0: View?) {
            selectedItem = adapterPosition
            notifyDataSetChanged()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        if (position == selectedItem) {
            holder.image.setBackgroundResource(R.color.main)
            DrawableCompat.setTint(holder.image.drawable, ContextCompat.getColor(holder.itemView.context, R.color.white))
        } else {
            holder.image.setBackgroundResource(R.color.white)
            DrawableCompat.setTint(holder.image.drawable, ContextCompat.getColor(holder.itemView.context, R.color.grey))
        }
        holder.image.setImageResource(categories[position].image)
        holder.title.text = categories[position].title
    }

    override fun getItemCount() = categories.size

    @SuppressLint("NotifyDataSetChanged")
    fun initializeList(list: List<CategoryModel>) {
        categories = list
        notifyDataSetChanged()
    }

    fun initializeItemClickListener(clickListener: OnItemClickListener) {
        itemClickListener = clickListener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun refreshAllElementsColors() {
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(imageView: ImageView)
    }
}