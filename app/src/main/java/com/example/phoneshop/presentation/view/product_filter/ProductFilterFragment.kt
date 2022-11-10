package com.example.phoneshop.presentation.view.product_filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.phoneshop.R
import com.example.phoneshop.databinding.FragmentProductFilterBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ProductFilterFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentProductFilterBinding
    private var brandTypes = listOf("Samsung", "Xiaomi Mi", "Iphone", "Motorola")
    private var priceTypes = listOf(
        "$300 - $500",
        "$500 - $3000",
        "$3000 - $8000",
        "$8000 - $10000"
    )
    private var sizeTypes = listOf(
        "4.5 to 5.5 inches",
        "5.5 to 6.5 inches",
        "6.5 to 6.7 inches"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_product_filter, container, false)
        binding = FragmentProductFilterBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            brand.setAdapter(ArrayAdapter(requireContext(), R.layout.dropdown_item, brandTypes))
            price.setAdapter(ArrayAdapter(requireContext(), R.layout.dropdown_item, priceTypes))
            size.setAdapter(ArrayAdapter(requireContext(), R.layout.dropdown_item, sizeTypes))
            doneButton.setOnClickListener {
                dismiss()
            }
            exitButton.setOnClickListener {
                dismiss()
            }
        }
    }
}