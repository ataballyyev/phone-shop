package com.example.phoneshop.presentation.view.cart

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.common.Constants
import com.example.domain.model.basket.BasketModel
import com.example.domain.model.network_result.NetworkResult
import com.example.domain.model.product_detail_model.ProductDetailModel
import com.example.phoneshop.R
import com.example.phoneshop.app.PhoneShopApplication
import com.example.phoneshop.databinding.FragmentCartBinding
import com.example.phoneshop.presentation.view.detail.ProductImagesAdapter
import com.example.phoneshop.presentation.viewmodel.MainViewModel
import com.example.phoneshop.presentation.viewmodel_factory.MainViewModelFactory
import javax.inject.Inject

class CartFragment : Fragment(R.layout.fragment_cart) {

    private lateinit var binding: FragmentCartBinding
    @Inject
    lateinit var viewModelFactory: MainViewModelFactory
    private lateinit var viewModel: MainViewModel
    private lateinit var basketProductModel: BasketModel
    private var adapter = MyCartAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCartBinding.bind(view)

        (requireActivity().applicationContext as PhoneShopApplication).appComponent.inject(this)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[MainViewModel::class.java]

        sendRequestProductDetails()
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun sendRequestProductDetails() {
        viewModel.apply {
            getBasketProducts(basketProductsApi = Constants.BASKET_PRODUCTS_API)
            liveBasketProducts.observe(viewLifecycleOwner) { response ->
                when(response) {
                    is NetworkResult.Success -> {
                        response.data?.let { model ->
                            basketProductModel = model
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
        binding.recyclerViewSavedProducts.adapter = adapter
        binding.recyclerViewSavedProducts.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter.initialize(list = basketProductModel.basket)
    }
}