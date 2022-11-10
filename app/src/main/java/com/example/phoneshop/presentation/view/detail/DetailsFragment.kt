package com.example.phoneshop.presentation.view.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.helper.widget.Carousel
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private lateinit var binding: FragmentDetailsBinding
    @Inject lateinit var viewModelFactory: MainViewModelFactory
    private lateinit var viewModel: MainViewModel
    private lateinit var productDetailModel: ProductDetailModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)

        (requireActivity().applicationContext as PhoneShopApplication).appComponent.inject(this)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[MainViewModel::class.java]

        sendRequestProductDetails()

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

        binding.carousel.setAdapter(object : Carousel.Adapter {
            override fun count(): Int = productDetailModel.images.size

            override fun populate(view: View?, index: Int) {
                Glide.with(view as ImageView)
                    .load(productDetailModel.images[index])
                    .into(view)
            }

            override fun onNewItem(index: Int) {

            }
        })
    }
}