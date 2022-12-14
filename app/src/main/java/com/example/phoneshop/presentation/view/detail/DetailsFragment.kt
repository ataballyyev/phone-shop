package com.example.phoneshop.presentation.view.detail

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.helper.widget.Carousel
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.domain.common.Constants
import com.example.domain.model.network_result.NetworkResult
import com.example.domain.model.product_detail_model.ProductDetailModel
import com.example.phoneshop.R
import com.example.phoneshop.app.PhoneShopApplication
import com.example.phoneshop.databinding.FragmentDetailsBinding
import com.example.phoneshop.presentation.viewmodel.MainViewModel
import com.example.phoneshop.presentation.viewmodel_factory.MainViewModelFactory
import javax.inject.Inject
import kotlin.math.abs

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private lateinit var binding: FragmentDetailsBinding
    @Inject lateinit var viewModelFactory: MainViewModelFactory
    private lateinit var viewModel: MainViewModel
    private lateinit var productDetailModel: ProductDetailModel
    private var adapter = ProductImagesAdapter()
    private var isLiked: Boolean = false

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)

        (requireActivity().applicationContext as PhoneShopApplication).appComponent.inject(this)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[MainViewModel::class.java]

        sendRequestProductDetails()
        binding.apply {
            backButton.setOnClickListener {
                findNavController().popBackStack()
            }
            tabLayout.addTab(tabLayout.newTab().setText("Shop"))
            tabLayout.addTab(tabLayout.newTab().setText("Details"))
            tabLayout.addTab(tabLayout.newTab().setText("Features"))
            colorFirst.setOnClickListener {
                imageViewFirstColor.isVisible = true
                imageViewSecondColor.isVisible = false
            }
            colorSecond.setOnClickListener {
                imageViewSecondColor.isVisible = true
                imageViewFirstColor.isVisible = false
            }
            button128.setOnClickListener {
                button128.background = requireContext().getDrawable(R.drawable.rounded_done_button)
                button128.setTextColor(requireContext().getColor(R.color.white))
                button256.background = requireContext().getDrawable(R.color.white)
                button256.setTextColor(requireContext().getColor(R.color.second))
            }
            button256.setOnClickListener {
                button256.background = requireContext().getDrawable(R.drawable.rounded_done_button)
                button256.setTextColor(requireContext().getColor(R.color.white))
                button128.background = requireContext().getDrawable(R.color.white)
                button128.setTextColor(requireContext().getColor(R.color.second))
            }
            shopButton.setOnClickListener {
                findNavController().navigate(DetailsFragmentDirections.actionDetailsFragmentToCartFragment())
            }
            likeButton.setOnClickListener {
                isLiked = if (isLiked) {
                    likeButton.setImageDrawable(requireContext().resources.getDrawable(R.drawable.unlike))
                    false
                } else {
                    likeButton.setImageDrawable(requireContext().resources.getDrawable(R.drawable.like))
                    true
                }
            }
        }

    }

    private fun sendRequestProductDetails() {
        viewModel.apply {
            getProductDetails(productDetailsApi = Constants.PRODUCT_DETAIL_API)
            liveProductDetails.observe(viewLifecycleOwner) { response ->
                when(response) {
                    is NetworkResult.Success -> {
                        response.data?.let { model ->
                            productDetailModel = model
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
        binding.apply {
            adapter.initializeImageList(list = productDetailModel.images)
            viewPagerProductImages.adapter = adapter
            viewPagerProductImages.offscreenPageLimit = 3
            viewPagerProductImages.clipChildren = false
            viewPagerProductImages.clipToPadding = false
            viewPagerProductImages.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            val transformer = CompositePageTransformer()
            transformer.addTransformer(MarginPageTransformer(40))
            transformer.addTransformer { page, position ->
                var r = 1 - abs(position)
                page.scaleY = 0.85f + r * 0.14f
            }
            viewPagerProductImages.setPageTransformer(transformer)
        }

    }
}