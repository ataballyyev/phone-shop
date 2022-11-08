package com.example.phoneshop.presentation.view.home

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.common.Constants
import com.example.domain.model.home.CategoryModel
import com.example.domain.model.home.HomeModel
import com.example.domain.model.network_result.NetworkResult
import com.example.phoneshop.R
import com.example.phoneshop.app.PhoneShopApplication
import com.example.phoneshop.databinding.FragmentHomeBinding
import com.example.phoneshop.presentation.viewmodel.MainViewModel
import com.example.phoneshop.presentation.viewmodel_factory.MainViewModelFactory
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import javax.inject.Inject

class HomeFragment : Fragment(R.layout.fragment_home), CategoryAdapter.OnItemClickListener {

    private lateinit var binding: FragmentHomeBinding
    @Inject lateinit var viewModelFactory: MainViewModelFactory
    private lateinit var viewModel: MainViewModel
    private lateinit var homeModel: HomeModel
    private val categoryAdapter = CategoryAdapter()
    private val sliderAdapter = ImageSliderAdapter()
    private val bestSellerAdapter = BestSellerProductsAdapter()

    private var listCategories: List<CategoryModel> = listOf(
        CategoryModel(title = "Phones", R.drawable.category_image_phone),
        CategoryModel(title = "Computer", R.drawable.category_image_computer),
        CategoryModel(title = "Health", R.drawable.category_image_health),
        CategoryModel(title = "Books", R.drawable.category_image_book),
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        (requireActivity().applicationContext as PhoneShopApplication).appComponent.inject(this)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[MainViewModel::class.java]

        sendRequestHomeProducts()

    }

    private fun sendRequestHomeProducts() {
        viewModel.apply {
            getHomeProducts(homeApi = Constants.HOME_API)
            liveHomeProducts.observe(viewLifecycleOwner) { response ->
                when(response) {
                    is NetworkResult.Success -> {
                        response.data?.let { model ->
                            homeModel = model
                            initializeUIElements()
                            Log.i("TAG", model.toString())
                        }
                    }
                    is NetworkResult.Error -> {
                        Log.i("TAG", "Error")
                    }
                    is NetworkResult.Loading -> {
                        Log.i("TAG", "Loading...")
                    }
                }
            }
        }
    }

    private fun initializeUIElements() {
        binding.recyclerViewCategory.adapter = categoryAdapter
        binding.recyclerViewCategory.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        categoryAdapter.initializeItemClickListener(this)
        categoryAdapter.initializeList(listCategories)

        binding.imageSlider.setSliderAdapter(sliderAdapter)
        sliderAdapter.initializeList(list = homeModel.home_store)
        binding.imageSlider.apply {
            setIndicatorAnimation(IndicatorAnimationType.WORM)
            setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
            autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH
            indicatorSelectedColor = Color.WHITE
            indicatorUnselectedColor = Color.GRAY
            scrollTimeInSec = 3
            startAutoCycle()
        }

        binding.recyclerViewBestSeller.adapter = bestSellerAdapter
        binding.recyclerViewBestSeller.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        bestSellerAdapter.initializeList(list = homeModel.best_seller)

    }

    override fun onItemClick(imageView: ImageView) {
        categoryAdapter.refreshAllElementsColors()
        imageView.setBackgroundResource(R.color.main)
        DrawableCompat.setTint(imageView.drawable, ContextCompat.getColor(imageView.context, R.color.white))
    }
}