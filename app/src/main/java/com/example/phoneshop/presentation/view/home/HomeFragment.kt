package com.example.phoneshop.presentation.view.home

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.model.home.CategoryModel
import com.example.phoneshop.R
import com.example.phoneshop.databinding.FragmentHomeBinding
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView

class HomeFragment : Fragment(R.layout.fragment_home), CategoryAdapter.OnItemClickListener {

    private lateinit var binding: FragmentHomeBinding
    private val categoryAdapter = CategoryAdapter()
    private val sliderAdapter = ImageSliderAdapter()

    private var listCategories: List<CategoryModel> = listOf(
        CategoryModel(title = "Phones", R.drawable.category_image_phone),
        CategoryModel(title = "Computer", R.drawable.category_image_computer),
        CategoryModel(title = "Health", R.drawable.category_image_health),
        CategoryModel(title = "Books", R.drawable.category_image_book),
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        initializeUIElements()

    }

    private fun initializeUIElements() {
        binding.recyclerViewCategory.adapter = categoryAdapter
        binding.recyclerViewCategory.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        categoryAdapter.initializeItemClickListener(this)
        categoryAdapter.initializeList(listCategories)

        binding.imageSlider.setSliderAdapter(sliderAdapter)
        binding.imageSlider.apply {
            setIndicatorAnimation(IndicatorAnimationType.WORM)
            setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
            autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH
            indicatorSelectedColor = Color.WHITE
            indicatorUnselectedColor = Color.GRAY
            scrollTimeInSec = 4
            startAutoCycle()
        }

    }

    override fun onItemClick(imageView: ImageView) {
        categoryAdapter.refreshAllElementsColors()
        imageView.setBackgroundResource(R.color.main)
        DrawableCompat.setTint(imageView.drawable, ContextCompat.getColor(imageView.context, R.color.white))
    }
}