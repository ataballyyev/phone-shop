package com.example.phoneshop.presentation.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.model.CategoryModel
import com.example.phoneshop.R
import com.example.phoneshop.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home), CategoryAdapter.OnItemClickListener {

    private lateinit var binding: FragmentHomeBinding
    private val adapter = CategoryAdapter()

    private var listCategories: List<CategoryModel> = listOf(
        CategoryModel(title = "Phones", R.drawable.category_image_phone),
        CategoryModel(title = "Computer", R.drawable.category_image_computer),
        CategoryModel(title = "Health", R.drawable.category_image_health),
        CategoryModel(title = "Books", R.drawable.category_image_book),
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        initializeRecyclerView()

    }

    private fun initializeRecyclerView() {
        binding.recyclerViewCategory.adapter = adapter
        binding.recyclerViewCategory.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        adapter.initializeItemClickListener(this)
        adapter.initializeList(listCategories)
    }

    override fun onItemClick(imageView: ImageView) {
        adapter.refreshAllElementsColors()
        imageView.setBackgroundResource(R.color.main)
        DrawableCompat.setTint(imageView.drawable, ContextCompat.getColor(imageView.context, R.color.white))
    }
}